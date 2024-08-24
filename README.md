#Installation guide
steps => 
    1. git clone the repository
    2. then start the application in itellij


Flow of applicaiton

    1. register user 
    2.then use the token in authencitcation
    3. curl request shoul have a bearer token

   # Curl Request is below =>
    
    curl -X POST "http://localhost:8080/api/suppliers/search?location=Seattle&manufacturingProcesses=printing_3d&capability=small_scale&page=0&size=10" \
     -H "Authorization: Bearer YOUR_AUTH_TOKEN"
