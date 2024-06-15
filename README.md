# Compiler Project in Java

This repository contains Java code for a comprehensive compiler project. The project is divided into several tasks, each focusing on a different aspect of compiler construction. Below is a brief overview of each task:

## Tasks Overview

### **Task 1: Regular Expression to Non-Deterministic Finite Automata (NFA) using Thompson’s Construction**
- **Description**: Implement a converter that transforms a given regular expression into an NFA using Thompson’s Construction method.

### **Task 2: Non-Deterministic Finite Automata (NFA) to Deterministic Finite Automata (DFA)**
- **Description**: Develop a procedure to convert an NFA into an equivalent DFA.

### **Task 3: Fallback Deterministic Finite Automata**
- **Description**: Extend the DFA implementation to support fallback mechanisms for improved error handling and recovery.

### **Task 4: Context-Free Grammars - Epsilon & Unit Rules Elimination**
- **Description**: Implement algorithms to eliminate epsilon (ε) and unit rules from context-free grammars.

### **Task 5: Context-Free Grammars - Left-Recursion Elimination**
- **Description**: Create a process to eliminate left recursion in context-free grammars to facilitate parsing.

### **Task 6: Context-Free Grammars - First and Follow**
- **Description**: Calculate the First and Follow sets for non-terminals in a given context-free grammar.

### **Task 7: ANTLR Lexical Analysis**
- **Description**: Use ANTLR to perform lexical analysis, generating tokens from the input source code.

### **Task 8: LL(1) Parsing**
- **Description**: Implement an LL(1) parser using the First and Follow sets to construct parsing tables.

### **Task 9: ANTLR Semantic Directed Definitions (SDD)**
- **Description**: Use ANTLR to implement an SDD where the start variable S has an attribute `check`. The value of `check` is 1 if the generated string represents a diagonal boolean matrix; otherwise, it is 0. The input strings are over the alphabet {0, 1, #}, representing a boolean matrix.

### **Task 10: Count Plateaus in a Sequence of Non-Negative Integers using ANTLR**
- **Description**: Use ANTLR to implement an SDT (Syntax-Directed Translation) or an SDD (Syntax-Directed Definition) to count the number of plateaus in a sequence of non-negative integers. A plateau is defined as a contiguous sub-sequence of two or more identical integers.

## Getting Started

### Prerequisites
- Java Development Kit (JDK)
- ANTLR (for Tasks 7, 9, and 10)

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/compiler-project.git
   cd compiler-project

