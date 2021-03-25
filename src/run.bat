@echo "The Rocket Scientist Problem"
@echo "print to output.txt, Network requests are printed to output.dat"
javac Main.java dataTypes/*.java utils/*.java primaryClasses/*.java 
java Main
echo "Opening output.dat file"
start notepad "output.dat"
PAUSE