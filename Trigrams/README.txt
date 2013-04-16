TAKS:
@@@@@@@@@@@@@@@@@@@@@@@@@@
1 read the file
2 split the words
3 determine the trigrams
3 mutate the text: construct the different possible sentences






APPROCH:
@@@@@@@@@@@@@@@@@@@@@@@@@@@@
Trigrams 
--------
TrigramAnalysis is the class responsible to read the file\text and split it in to words. Next job is to identify the trigrams. 
Trigram class reperesented the single trigram. TirgramAnalysis class generates a list of Trigram objects based on the text or the file passed in.

mutate the text
---------------
Once all the trigrams are generated the next task is to generate all the possible word sequence from the trigram.

Reading large file
------------------

I have used file channel here to make the large file reading more easy. one more thing I did in this is that instead of reading the whole file and identify the trigram,
I read the file text chunk by chunk, that will reduce the risk of outofmemory..






