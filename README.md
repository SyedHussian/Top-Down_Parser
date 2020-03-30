# Top-Down_Parser
Top-Down Parser

Implementing a top-down, recursive-descent parser for the following BNF for our project language.

⟨fun def list⟩ → ⟨fun def⟩ | ⟨fun def⟩ ⟨fun def list⟩                                                                                       
⟨fun def⟩ → ⟨header⟩ "{" ⟨exp⟩ "}"                                                                                                         
⟨header⟩ → ⟨fun name⟩ ⟨parameter list⟩                                                                                                     
⟨fun name⟩ → ⟨id⟩                                                                                                                         
⟨parameter list⟩ → ε | ⟨id⟩ ⟨parameter list⟩                                                                                               
⟨exp⟩ → ⟨id⟩ | ⟨int⟩ | ⟨float⟩ | ⟨floatE⟩ | ⟨floatF⟩ | "nil" | "(" ⟨fun exp⟩ ")" | "if" ⟨exp⟩ "then" ⟨exp⟩ "else" ⟨exp⟩                         
⟨fun exp⟩ → ⟨fun op⟩ ⟨exp list⟩                                                                                                           
⟨fun op⟩ → ⟨id⟩ | "pair" | "first" | "second" | ⟨arith op⟩ | ⟨bool op⟩ | ⟨comp op⟩                                                           
⟨arith op⟩ → + | − | * | /                                                                                                               
⟨bool op⟩ → "or" | "and" | "not"                                                                                                         
⟨comp op⟩ → "<" | "<=" | ">" | ">=" | "="                                                                                                 
⟨exp list⟩ → ε | ⟨exp⟩ ⟨exp list⟩                                                                                                         

NOTE: ε stands for the empty string.

A lexical analyzer for this language's tokens has been implemented in Project 1.

This program will read any text file that contains (what is intended to be) a string in the category ⟨fun def list⟩. It will then construct an explicit parse tree and display it in linearly indented form: 

For example, the linearly indented form of the tree:

Tree to Linearly intended form: 

                  A                  
                  |                          
       -----------------------               
      |           |           |              
      B           C           D           
                  |                          
          ------------------                 
         |                  |                
         E                  F           
         |                                   
      -------                                  
     |       |
     G       H
     
     
                 0 A                                           
                  \                            
                  1 B                 
                   |                       
                  1 C                                   
                    \                  
                    2 E                   
                     \                 
                     3 G             
                      |                 
                     3 H                          
                      /                 
                    2 F                
                    /                 
                  1 D                         
                                        
            
            
    
Each syntactic category name labeling a node is displayed on a separate line, prefixed with the integer i representing the node's depth and indented by i blanks. This is a basic form of syntax profiler.
