import scipy
import pandas as pd
from tabulate import tabulate
import sys


# CSV file column headers
cols = ["Size", "McCabe", "Readability"]


# Prints a readable table from the values given to it.
# Values should be a list containing each row as a list.
def printTable(values):
    table = [["Metrics", "Pearson", "p-value", "Spearman", "p-value", "Kendall's Tau", "p-value"]]
    table.extend(values)
    print(tabulate(table, headers="firstrow", tablefmt="simple_grid"))


# Returns all input directories specified by user in command line arguments
def parseInputFiles(args):
    return args[1:]


def main():

    # Check if user specified CSV paths
    if len(sys.argv) < 2:
        print("ERROR: Please include the CSV file paths")
        return

    for file_path in parseInputFiles(sys.argv):
        try:
            # Read CSV files as pandas dataframes
            df = pd.read_csv(file_path, header="infer", skipinitialspace=True)
            print()
            print("======================= " + file_path + " =======================", "\n")
            values = []

            # Compute correlations between Number of Revisions and other columns
            for col in cols:
                pearson = scipy.stats.pearsonr(df["#Revisions"], df[col])
                spearman = scipy.stats.spearmanr(df["#Revisions"], df[col])
                kendall = scipy.stats.kendalltau(df["#Revisions"], df[col])
                values.extend([["#Revisions-" + col, pearson.statistic, pearson.pvalue, spearman.statistic, spearman.pvalue, kendall.statistic, kendall.pvalue]])
            
            printTable(values)
        except:
            print("ERROR: Invalid file path.")


if __name__ == "__main__":
    main()