class Main {
  public static void main(String[] args) {
    (new Main()).init();
  }
  void print(Object o){ System.out.println(o);}
  void printt(Object o){ System.out.print(o);}

  void init(){
   

   char[] sub = {'1','3','5','7','9','2','4','6','8','0'};
   char[] sub2 = {'a','b','c','d','e','A','B','C','D','E'};
    // Encoding the plaintext:
    String file = Input.readFile("Original.txt");
    // Encode level 1 (substitution)
    String encodedMsg1 = substitute(file,sub2,sub);
    Input.writeFile("Encode1.txt", encodedMsg1);
    String encodedMsg2 = athConversion(encodedMsg1);
    Input.writeFile("Encode2.txt",encodedMsg2);
    // // Encode level 3 (string manipulation - reverse)
    String encodedMsg3 = reverse(encodedMsg2);
    Input.writeFile("Encode3.txt", encodedMsg3);

 

        // Decoding the ciphertext:

        String file2 = Input.readFile("Encode3.txt");
        // Decode level 1
        String decodedMsg1 = reverse(file2);
        Input.writeFile("Decode1.txt", decodedMsg1);
      // Decode level 2
        String decodedMsg2= htaConversion(decodedMsg1);
        Input.writeFile("Decode2.txt", decodedMsg2);
 
        // Decode level 3
        String decodedMsg3 = substitute(decodedMsg2, sub, sub2);
        Input.writeFile("Decode3.txt", decodedMsg3);
   
  }

  String reverse(String s){
    String bld="";
    for(int x=s.length()-1; x>=0; x--)
      bld+=s.substring(x,x+1);

    return bld;
  }

   String athConversion(String txt) {

    char[] chars = txt.toCharArray(); /*Converts string to char array*/
    StringBuffer hex = new StringBuffer(); /* A string buffer is a modifiable string*/
  
    for (int x = 0; x < chars.length; x++) {
      hex.append(Integer.toHexString((int) chars[x])); /*Converts integer to its hexadecimal equivalent string */
    }
    return hex.toString();/*Returns the changed string buffer*/
  }



   String htaConversion(String txt) {

    StringBuilder output = new StringBuilder(""); //string builder allows you to modify the string after creation
  
    for (int x = 0; x < txt.length(); x += 2) {
      String str = txt.substring(x, x + 2); //splitting the string into groups of 2 characters at a time
      output.append((char) Integer.parseInt(str, 16)); //converting the two characters into its corresponding integer value and casting it into a character
    }
    return output.toString(); //concatenate() all chars together into string 
  }


 
  // Substitution encoding
  String substitute(String s, char[] sub, char[] sub2){
    String build = "";
    char ch ='\0';
    int index=0;
   
    for(int x=0; x<=s.length()-1; x++){
      ch = s.charAt(x);
      index = indexOf(ch,sub);
      if(index != -1){
        build += sub2[index];
      }
      else{
        build += ch;
      }
    }
    return build;
  }


  // identifying index of char within array
  int indexOf(char ch, char[] arry){
    for(int x=0; x<=arry.length-1; x++){
      if(arry[x] == ch){
        return x;
      }
    }
    return -1;
  }


}