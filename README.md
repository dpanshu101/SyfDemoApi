# SyfDemoApi

Execute the jar file using below command:

java -jar syfdemoapi-0.0.1-SNAPSHOT.jar


Screenshots of steps available on below url:
https://github.com/dpanshu101/SyfDemoApi/tree/master/screenshots


Step #1:

Create a test user from below endpoint:

curl -X POST \
  http://localhost:8098/user \
  -H 'Accept: */*' \
  -H 'Content-Type: application/json' \
  -d '   {
        "username": "test",
        "password": "test"
    }'
    
Step #2:

Retrieve all users from below endpoint

curl -X GET \
  http://localhost:8098/user \
  -H 'Content-Type: application/json' \


Step #3 

Upload images, use below endpoint:
NOTE: USE FILE UPLOAD IN POSTMAN

curl -X POST \
  http://localhost:8098/images \
  -H 'Accept: */*' \
  -H 'Authorization: Basic dGVzdDp0ZXN0' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -F 'file=@/home/deepanshu/Pictures/test.png'
  
Step #4:
Get All Images:

curl -X GET \
  http://localhost:8098/images \
  -H 'Accept: */*' \
  -H 'Authorization: Basic dGVzdDp0ZXN0' \
  
Step #5 
Delete Image:

curl -X DELETE \
  'http://localhost:8098/images/?deleteHash=7q0Qa0ERwl7pV8T' \
  -H 'Accept: */*' \
  -H 'Authorization: Basic YWRtaW46YWRtaW4=' \
