Description
This project is an API for converting measurements based on a custom encoding scheme. It provides a RESTful endpoint that accepts an input value and converts it into a list of integers representing the converted measurements. The API handles special cases and provides error handling for invalid inputs.

Usage : 
To use this project, you will need to have Docker installed on your machine.

Installation :
To run the Measurement Conversion API locally, follow these steps:

1. Clone the repository:
git clone https://github.com/Najlaalrajhia98/PackageMeasurementConversion.git

2. Change to the project directory: 

 cd project directory

3. Build the Docker image:
docker build -t measurement-conversion-api .

4. Run the Docker container:

docker-compose up

5. The API will be accessible at http://localhost:8080/convert-measurements.

 Usage
To convert measurements using the API, send a GET request to the /convert-measurements endpoint with the input parameter set to the value you want to convert. The API will return a JSON response with the converted measurements.

Example usage at the browser :
http://localhost:8080/convert-measurements?input=aaaa


Example response:
[
1,
1
]

Input Validation
The API performs input validation to ensure that the input value is valid for conversion. It checks for the presence of numbers in the input string. If a number is found, the API will return a 400 Bad Request response with an error message.

Example Input : 
123
Example error response:
Invalid input: 123


Error Handling
If an unexpected error occurs during the conversion process, the API will return a 400 Bad Request response with an error message describing the issue.

Example error response:
Error while processing input: zz 'Enter a valid Input!'

To test the API and validate the expected output, you can use the following Python Script :

import requests

url = 'http://localhost:8080/convert-measurements'

expected_io = [('aa', [1]),
               ('abbcc', [2, 6]),
               ('dz_a_aazzaaa', [28, 53, 1]),
               ('a_', [0]),
               ('abcdabcdab', [2, 7, 7]),               ('abcdabcdab_', [2, 7, 7, 0]),
               ('zdaaaaaaaabaaaaaaaabaaaaaaaabbaa', [34]),
               ('zza_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_a_', [26]),
               ('za_a_a_a_a_a_a_a_a_a_a_a_a_azaaa', [40, 1])]


for req_inp, expected_output in expected_io:
    response = requests.get(url, params={'input': req_inp})
    output = response.json()

    assert output == expected_output, "Unexpected Output"











