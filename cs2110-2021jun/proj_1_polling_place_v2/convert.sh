ls -1 *.dot | awk -F. '{print $1}' | while read DOT; do
    dot -Tpdf $DOT.dot -o $DOT.pdf
done
