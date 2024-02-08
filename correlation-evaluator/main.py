import scipy
import pandas as pd
import sys

cols = ["Size", "McCabe", "Readability"]

def main():

    if len(sys.argv) < 2:
        print("ERROR: Please include the CSV file path")
        return

    file_path = sys.argv[1]

    try:
        df = pd.read_csv(file_path, header="infer", skipinitialspace=True)
        print()
        print("========= Pearson Correlation Coefficient =========\n")
        for col in cols:
            coeff = scipy.stats.pearsonr(df["#Revisions"], df[col])
            print("#Revisions-" + col + ": ", coeff[0], " P-value: ", coeff[1])

        print()
        print("========= Spearman Correlation Coefficient =========\n")
        for col in cols:
            coeff = scipy.stats.spearmanr(df["#Revisions"], df[col])
            print("#Revisions-" + col + ": ", coeff[0], " P-value: ", coeff[1])

        print()
        print("========= Kendall’s Tau Correlation Coefficient =========\n")
        for col in cols:
            coeff = scipy.stats.kendalltau(df["#Revisions"], df[col])
            print("#Revisions-" + col + ": ", coeff[0], " P-value: ", coeff[1])
    except:
        print("ERROR: Invalid file path.")



if __name__ == "__main__":
    main()