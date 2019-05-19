# top-collegues-api

top-collegues-api est une application back-end et stand-alone de vote pour le meilleur employé du mois, conçue dans le cadre d'une formation à diginamic pour le compte de DTA Ingénierie. Elle nécessite l'application collegues-api, Api de gestion des employés d'une société, également présente sur mes pages github. Les fonctionnalités de top-collegues-api sont disponibles à l'adresse suivante:  
http://pennecot-top-collegues-api.herokuapp.com
Cette application est toujours en développement et susceptible d'évoluer (ajout ou modification de fonctionnalités)  
  
Les opérations actuellement offertes par top-collegues-api sont les suivantes:

authentification, nécessaire pour l'accés aux autres fonctionnalités.  
POST "/auth"  
Nécessite le corps de requête suivant:  
{  
"email": monEmail,  
"password": password  
}  
Un jeu de donnée test, susceptible d'évoluer, est disponible par défaut. Vous pouvez vous connecter avec les utilisateurs suivants:
email, password: "vador@blackStar.com", "toto"   
email, password: "palpatine@empire.com", "titi"  
email, password: "sion@marmiton.com", "toto"  
email, password: "sion@vivelessith.com", "toto"  
email, password: "astier@kaamelot.com", "toto  
  
Renvoie l'utilisateur actuellement connecté  
GET  "/me"  
  
Déconnexion de l'utilisateur connecté (ne nécessite pas de corps de requête)  
POST "/logout"  

Gestion des participants:  
  
Récupération d'un participant par son matricule:  
GET "/participants/{matricule}"  
  
Gestion des votes:  
  
Création d'un vote:  
POST "/votes/add  
Nécessite un corps de requête décrivant un vote au format suivant:  
{  
  "targetEmail": email,  
  "voteType": voteType  
}  
voteType peut prendre 2 valeurs: "LIKE" ou "DISLIKE"  
  
Récupération des votes ciblant un participant:  
GET "/votes/{matricule}  
ou {matricule est le matricule du participant dont on veut récupérer les votes le ciblant (pour récupérer les votes effectués par le participant lui-même, récupérer le participant par un get sur son matricule, les participants connaissant les votes qu'ils ont effectués.  

