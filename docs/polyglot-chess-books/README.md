# About

Polyglot bin books are chess opening books to be used with chess GUIs such as Scid vs PC, xboard/winboard. Scid vs PC comes with 4 general purpose polyglot books preinstalled, namely, gm2600.bin, Elo2400.bin, Performance.bin and varied.bin. Here I am making available several more polyglot books. I think they are useful if you want  to practice against a specific book like king's gambit book or an opening book of Indian defenses or a book of your repertoire.

# Description of books and how to make them

40h- books are created using an excellent source of very high quality grandmaster games from http://www.nk-qy.info/40h/ That repository is maintained by Norman Pollock. (Many thanks!)

Currently (as of 2022.02.20) 679661 games are available in that repository. Please see the readme file from that repository http://www.nk-qy.info/40h/readme-pgn-db.txt to know what criteria were used by Norman Pollock to curate the games. The games come in 4 pgn files, namely, gm1980.pgn, gm1931.pgn, gm1981.pgn and gm2016.pgn. I combined them using pgn-extract, while also removing games that contain setup tags (start with non-standard positions).

pgn-extract --nosetuptags gm*.pgn -o40h-all.pgn 

I split the games in 40h-all.pgn by ECO codes A, B, C, D, E, using the program pgn-extract by David J. Barnes. See https://www.cs.kent.ac.uk/people/staff/djb/pgn-extract/ for more information. (Thanks for an amazing program!). To know more about the ECO codes of chess openings, see https://en.wikipedia.org/wiki/Encyclopaedia_of_Chess_Openings.

The command to split a pgn into 5 pgns A.pgn to E.pgn is

pgn-extract -E1 40h-all.pgn

Rename A.pgn to 40h-A.pgn, etc, in case we create similar files from other databases.

mv A.pgn 40h-A.pgn
...

Next I ran the program polyglot (version 2.0.4) by Fabien Letouzey to build the books as follows.

polyglot make-book -pgn 40h-A.pgn -max-ply 24 -bin 40h-A24p.bin 
polyglot make-book -pgn 40h-B.pgn -max-ply 24 -bin 40h-B24p.bin 
polyglot make-book -pgn 40h-C.pgn -max-ply 24 -bin 40h-C24p.bin 
polyglot make-book -pgn 40h-D.pgn -max-ply 24 -bin 40h-D24p.bin 
polyglot make-book -pgn 40h-E.pgn -max-ply 24 -bin 40h-E24p.bin 
polyglot make-book -pgn 40h-all.pgn -max-ply 24 -bin 40h-24p.bin

The suffix 24p indicates that the book lines are maximum 24 plies (half moves). The last one contains all ECO codes.

I then extracted King's Gambit games from C.pgn as follows.

pgn-extract -TeC3 C.pgn -okg-40h.pgn

I similarly extracted two other King's Gambit files - one from Caissabase (https://caissabase.co.uk/ restricting to games between players rated 2100 or above) and CCRL (Computer Chess Rating List - https://ccrl.chessdom.com/ccrl/4040/ where currently 1357739 games are available from engine matches). The caissabase comes in scid vs pc format. You have to open the database in scid vs pc, filter games for players rated 2100 or above, with ECO codes C30-C39 for King's Gambit, and export all filtered games in pgn format (say, kg-caissa.pgn). Extract King's Gambit games from ccrl.pgn by running

pgn-extract --nosetuptags -TeC3 ccrl.pgn -okg-ccrl.pgn

I combined the three King's Gambit files using pgn-extract as follows:

pgn-extract kg*.pgn -okings-gambit.pgn

I then created a 24 ply King's Gambit book by running

polyglot make-book -pgn kings-gambit.pgn -max-ply 24 -bin kings-gambit-24p.bin

Similar bin books may be created for the repertoire of Carlsen or other players or for other specific openings. I plan to create books for Queen's Gambit, Ruy Lopez and Sicilian also.

# Usage

Copy the polyglot bin book files to the directory from where your chess engine can read them. For example, in scid vs pc, on my installation it is /usr/local/share/scid/books and on Android I copied them to the book directory of DroidFish. In winboard/xboard, you can keep the files anywhere and configure the engines to read them from the appropriate path. In DroidFish, before starting a new game, select the book against which you want to play a game. 

These books are quite suitable for practicing specific openings or classes of openings. I welcome feedback.

# No warranty 

No warranty, expressed or implied, is made that the information provided here is useful or accurate. The user bears full responsibility for any consequences from the use or misuse of these files. The author of these files is not responsible for any damages or losses of any kind. You may distribute them 'as is'. The author of these files is not responsible for any changes made by others.
