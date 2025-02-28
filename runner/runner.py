import subprocess


def run_script(script_path):
    try:
        # Run the script and capture the output
        result = subprocess.run(
            ['python', script_path],  # Command to execute the script
            text=True,  # Ensure output is returned as a string
            capture_output=True  # Capture stdout and stderr
        )

        # Check if the script ran successfully
        if result.returncode == 0:
            print("Script executed successfully!")
            print("Output:")
            print(result.stdout)  # Print the standard output
        else:
            print("Script failed with error:")
            print(result.stderr)  # Print the standard error

    except Exception as e:
        print(f"An error occurred: {e}")


if __name__ == "__main__":
    # Path to the script you want to run
    script_path = 'script.py'  # Replace with your script's path

    # Run the script
    run_script(script_path)