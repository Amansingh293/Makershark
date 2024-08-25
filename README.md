#Installation guide
steps => 
    1. git clone the repository
    2. then start the application in intellij


Flow of applicaiton

    1. register user 
    2.then use the token in authencitcation (You can retrieve this via login api also)
    3. curl request shoul have a bearer token

   # Curl Request is below =>
    
   # Search curl request =>
   
   curl -X POST "http://localhost:8080/api/suppliers/search?location=Seattle&manufacturingProcesses=printing_3d&capability=small_scale&page=0&size=10" \
     -H "Authorization: Bearer YOUR_AUTH_TOKEN"


# Login curl request =>
    curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"email": "yourEmail", "password": "yourPassword"}'

# Register request =>

curl -X POST http://localhost:8080/api/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username": "yourUsername", "password": "yourPassword", "email": "yourEmail@example.com"}'
