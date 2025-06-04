# ForensicAnalysis

A Java-based application that performs forensic analysis on input data files by reading structured input files, processing the data to extract meaningful insights, and outputting the results to check for matches.

## Features

- **Data Parsing**: Efficiently reads and parses input files containing DNA Profiles.
- **Analysis Engine**: Implements BST Traversal algorithms to analyze the parsed data for flagged and matching profiles.
- **Result Generation**: Outputs the analysis results in a readable format to show existing matches between the two input profiles and the unmatched profiles.

## Suggested Prerequisites For Running

- **Java Development Kit (JDK)**: Ensure that JDK 11 or higher is installed on your system.
- **Visual Studio Code (VS Code)**: Recommended IDE for editing and running the project.
- **Java Extension Pack**: Install this extension in VS Code for Java development support.

## Project Structure

ForensicAnalysis/  
├── .vscode/           # VS Code specific settings  
├── bin/               # Compiled bytecode files  
├── src/               # Java source files  
├── input1.in          # Sample input file 1  
├── input2.in          # Sample input file 2  
├── input3.in          # Sample input file 3  
└── README.md          # Documentation  

## Getting Started (Using VS Code)

### 1. Clone the Repository

git clone https://github.com/HarishKrishnaan/ForensicAnalysis.git  
cd ForensicAnalysis

### 2. Open in VS Code

- Launch **Visual Studio Code**.  
- Open the `ForensicAnalysis/` folder.  
- If prompted, install the recommended Java extensions.

### 3. Compile the Code

Open the VS Code terminal and run:

javac -d bin src/*.java

This compiles all `.java` files in the `src/` directory and places the compiled `.class` files in the `bin/` directory.

### 4. Run the Application

To run the program using one of the sample input files:

java -cp bin Main input1.in

Replace `input1.in` with any of the provided files (`input2.in`, `input3.in`) or your own input file.

### 5. View the Output

- The results of the program will be printed to the terminal.  
- Possible matching profiles will be displayed from the input data. 

## Notes

- If creating custom input files, ensure your input files follow the expected format to run Forensic Analysis properly.
- Feel free to comment on my program with any bugs, issues, or suggestions.
