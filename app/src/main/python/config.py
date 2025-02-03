from dotenv import load_dotenv
import os

load_dotenv()  # Φορτώνει τις μεταβλητές περιβάλλοντος από το αρχείο .env

class Config:
	FIREBASE_PRIVATE_KEY = os.getenv("FIREBASE_PRIVATE_KEY")
	FIREBASE_PROJECT_ID = os.getenv("FIREBASE_PROJECT_ID")
	FIREBASE_CLIENT_EMAIL = os.getenv("FIREBASE_CLIENT_EMAIL")
	FIREBASE_CLIENT_ID = os.getenv("FIREBASE_CLIENT_ID")
	FIREBASE_AUTH_URI = os.getenv("FIREBASE_AUTH_URI")
	FIREBASE_TOKEN_URI = os.getenv("FIREBASE_TOKEN_URI")
	FIREBASE_AUTH_PROVIDER_X509_CERT_URL = os.getenv("FIREBASE_AUTH_PROVIDER_X509_CERT_URL")
	FIREBASE_CLIENT_X509_CERT_URL = os.getenv("FIREBASE_CLIENT_X509_CERT_URL")
