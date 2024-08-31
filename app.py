from flask import Flask, request, jsonify
import subprocess
import json

app = Flask(__name__)

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json

    # Convert JSON data to a string representation that can be passed to Java
    input_data = json.dumps(data)

    # Run the Java program with the provided input data
    # Ensure Main class is correctly referenced and classpath is correct
    result = subprocess.run(['java', '-cp', '.;h2o-genmodel.jar', 'Main', input_data],
                            capture_output=True, text=True, env={"CLASSPATH": ".;h2o-genmodel.jar"})

    if result.returncode != 0:
        return jsonify({"error": result.stderr.strip()}), 500

    # Parse the output from the Java program
    output = result.stdout.strip()
    return jsonify({"prediction": output})

if __name__ == '__main__':
    app.run(debug=True)
