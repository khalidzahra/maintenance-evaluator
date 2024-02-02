# Measurement Evaluator
This project aims to indicate the change-proneness of a method using code metrics,
and find out if there are statistically significant correlations between them. Specifically, CodeShovel was used to 
extract 73,733 methods from [Hadoop](https://github.com/apache/hadoop) and 
3,733 methods from [Checkstyle](https://github.com/checkstyle/checkstyle) into JSON files that contain their bodies and 
change histories.
## Output
For each method, the project calculates the size, McCabe complexity, readability, and #Revisions. These metrics are then
output into CSV files, one for each project, in the following format:
|  JSON # 	|   Size (SLOC) 	|   McCabe  	|   Readability 	|   #Revisions  	|
|---	|---	|---	|---	|---	|

After that, a python script is used to calculate the Pearson, Spearman, and Kendall's Tau correlation coefficients and
P-values.
