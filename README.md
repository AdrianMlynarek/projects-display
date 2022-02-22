# projects-display

Memsource Integration Tool


1. BootRun the application
2. Open Postman or different REST tool and execute [POST] http://localhost:8080/login endpoint to sync tool with your Memsource account. Sample payload:
   
{  
    "username": "your@account.com",  
    "password": "your_password"  
}

3. Use [GET] http://localhost:8080/projects endpoint to get list of your created projects

The expected response is JSON list of your Memsource
