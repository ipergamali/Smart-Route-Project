import firebase_admin
import json
import os
from firebase_admin import credentials, firestore

# Αρχικοποίηση της σύνδεσης με το Firebase
# Καθορισμός του σωστού path
script_dir = os.path.dirname(os.path.abspath(__file__))  # Παίρνει το directory του script
json_path = os.path.join(script_dir, "serviceAccountKey.json")

# Φόρτωση του Firebase Certificate
cred = credentials.Certificate(json_path)
firebase_admin.initialize_app(cred)
# Σύνδεση με το Firestore
db = firestore.client()

# Ανάγνωση του JSON αρχείου που περιέχει τη δομή της βάσης δεδομένων
with open('../java/com/ioannapergamali/smartroute/db/Firestore_Structure.json', 'r') as f:
    firestore_structure = json.load(f)

# Συνάρτηση για να διαγράψουμε τις υπάρχουσες συλλογές (όταν ξεκινάμε)
def delete_collection(collection_ref, batch_size=10):
    docs = collection_ref.limit(batch_size).stream()
    deleted_docs = 0
    for doc in docs:
        doc.reference.delete()
        deleted_docs += 1
    print(f"Διαγράφηκαν {deleted_docs} έγγραφα από τη συλλογή {collection_ref.id}.")
    if deleted_docs >= batch_size:
        # Επαναλαμβάνουμε τη διαδικασία για να διαγραφούν όλα τα έγγραφα
        delete_collection(collection_ref, batch_size)

# Συνάρτηση για να δημιουργήσουμε τη βάση δεδομένων με δυναμικό τρόπο από το JSON
def initialize_firestore():
    for collection_name, collection_data in firestore_structure.items():
        # Διαγραφή της υπάρχουσας συλλογής αν υπάρχει
        collection_ref = db.collection(collection_name)
        delete_collection(collection_ref)
        
        print(f"Δημιουργία συλλογής: {collection_name}")
        
        for document_name, document_data in collection_data.items():
            # Δημιουργία εγγράφου με το όνομα του χρήστη/οχήματος κλπ.
            document_ref = collection_ref.document(document_name)
            print(f"Δημιουργία εγγράφου: {document_name}")
            
            # Έλεγχος για την ύπαρξη του πεδίου 'fields' πριν την προσθήκη
            if 'fields' in document_data:
                # Δημιουργία πεδίων για το έγγραφο
                document_ref.set({field: None for field in document_data['fields']})
                print(f"Πεδία για το έγγραφο {document_name}: {document_data['fields']}")
            else:
                print(f"Προσοχή! Δεν βρέθηκαν πεδία για το έγγραφο {document_name}.")
            
            # Αν υπάρχουν υποσυλλογές, δημιουργήστε τις
            if 'subcollections' in document_data:
                for subcollection_name, subcollection_data in document_data['subcollections'].items():
                    subcollection_ref = document_ref.collection(subcollection_name)
                    print(f"Δημιουργία υποσυλλογής: {subcollection_name} για το έγγραφο {document_name}")
                    for subdocument_name, subdocument_data in subcollection_data.items():
                        # Έλεγχος για την ύπαρξη του πεδίου 'fields' στην υποσυλλογή
                        if 'fields' in subdocument_data:
                            # Δημιουργία εγγράφου για την υποσυλλογή
                            subdocument_ref = subcollection_ref.document(subdocument_name)
                            subdocument_ref.set({field: None for field in subdocument_data['fields']})
                            print(f"Δημιουργία εγγράφου στην υποσυλλογή: {subdocument_name} με πεδία {subdocument_data['fields']}")
                        else:
                            print(f"Προσοχή! Δεν βρέθηκαν πεδία για το έγγραφο στην υποσυλλογή {subdocument_name}.")

if __name__ == "__main__":
    initialize_firestore()
    print("Η βάση δεδομένων Firestore έχει αρχικοποιηθεί δυναμικά από το JSON.")
