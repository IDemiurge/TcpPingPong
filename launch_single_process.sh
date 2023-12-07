CLASSPATH=target/classes
MAIN_CLASS=Launcher
java -cp "$CLASSPATH" $MAIN_CLASS "single"
read -n 1 -s -r -p "Press any key to continue"
