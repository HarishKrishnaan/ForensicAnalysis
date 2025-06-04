# ForensicAnalysis

A Java-based application designed to perform forensic analysis on input data files. This project reads structured input files, processes the data to extract meaningful insights, and outputs the results for further examination.

## Features

- **Data Parsing**: Efficiently reads and parses input files containing forensic data.
- **Analysis Engine**: Implements algorithms to analyze the parsed data for patterns and anomalies.
- **Result Generation**: Outputs the analysis results in a readable format for investigators.

## Prerequisites

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
└── README.md          # Project documentation  

## Getting Started

### 1. Clone the Repository

git clone https://github.com/HarishKrishnaan/ForensicAnalysis.git  
cd ForensicAnalysis

### 2. Open in VS Code

- Launch **Visual Studio Code**.  
- Open the `ForensicAnalysis/` folder.  
- If prompted, install the recommended Java extensions.

### 3. Compile the Code

Open the integrated terminal in VS Code and run:

javac -d bin src/*.java

This compiles all `.java` files in the `src/` directory and places the compiled `.class` files in the `bin/` directory.

### 4. Run the Application

To run the program using one of the sample input files:

java -cp bin Main input1.in

Replace `input1.in` with any of the provided files (`input2.in`, `input3.in`) or your own input file.

### 5. View the Output

- The results of the forensic analysis will be printed to the terminal.  
- Interpret the console output as the findings derived from the input data.

## Notes

- Ensure your input files follow the expected format for proper analysis.  
- You can customize the analysis logic by modifying or extending the Java classes in `src/`.

## Contributing

Contributions are welcome! Fork the repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License.
