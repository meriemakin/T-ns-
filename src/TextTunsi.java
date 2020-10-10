import java.util.Hashtable;

public class TextTunsi {

    private char[] textOldTunsi;
    private char[] textModernTunsi;
    private Hashtable<Character,Character> modernTunsiLetters;

    public TextTunsi()
    {
        this.setmodernTunsiLetters();
    }

    public void setmodernTunsiLetters()
    {
        Hashtable<Character, Character> table =  new Hashtable<Character, Character>();
        /*without or deduced from writing that it has to has to be only this way*/
        table.put('\u0621', ' '); /*ء*/
        table.put('\u0623', 'e'); /*أ*/
        table.put('\u0627', 'ê'); /*ا*/
        table.put('\u0622', 'ê'); /*آ*/
        table.put('\u0649', 'ê'); /*ى*/
        table.put('\u0624', 'u'); /*ؤ*/
        table.put('\u0625', 'i'); /*إ*/
        table.put('\u0626', 'i'); /*ئ*/
        table.put('\u0628', 'b'); /*ب*/
        table.put('\u062B', '\u03D1'); /*θ-ث*/
        table.put('\u062C', 'j'); /*ج*/
        table.put('\u062E', '\u03C7'); /*χ-خ*/
        table.put('\u062F', 'd'); /*د*/
        table.put('\u0630', '\u03B6'); /*ζ-ذ!!!!!!!!!!!!!!!!1*/
        table.put('\u0636', '\u03B6'); /*ζ-!!!!!!!!!!!!!!!!!!ض*/
        table.put('\u0638', '\u03B6'); /*ζ-ظ!!!!!!!!!!!!!!!!!*/
        table.put('\u0631', 'r'); /*ر*/
        table.put('\u0632', 'z'); /*ز*/
        table.put('\u0633', 's'); /*س*/
        table.put('\u0635', 's'); /*ص*/
        table.put('\u0634', '\u015F'); /*ş-ش*/
        table.put('\u0637', 't'); /*ط*/
        table.put('\u062A', 't'); /*ت*/
        table.put('\u0639', '\u03B1'); /*α-ع*/
        table.put('\u063A', '\u03B3'); /*γ-غ Here another possibility could be the hebrew letter U+05E8	*/
        table.put('\u0641', 'f'); /*ف*/
        table.put('\u0642', 'k'); /*ق*/
        table.put('\u0643', 'k'); /*ك*/
        table.put('\u0644', 'l'); /*ل*/
        table.put('\u0645', 'm'); /*م*/
        table.put('\u0646', 'n'); /*ن*/
        table.put('\u0629', 'h'); /*ة*/
        table.put('\u0647', 'h'); /*ه*/
        table.put('\u062D', 'h'); /*ح*/
        table.put('\u0648', 'w'); /*و*/
        table.put('\u064A', 'y'); /*ي*/
        table.put('\u068A', 'g'); /*ڨ*/

        this.modernTunsiLetters = table;
    }

    public Hashtable<Character, Character> getModernTunsiLetters()
    {
        return modernTunsiLetters;
    }

    public void setTextOldTunsi(String text)
    {
        char [] characters = text.toCharArray();
        this.textOldTunsi = characters;
    }

    public char[] getTextOldTunsi()
    {
        return textOldTunsi;
    }


    public void setTextModernTunsi()
    {
        this.textModernTunsi = new char[this.textOldTunsi.length];
        int countModernText = 0;
        for(int count = 0; count<this.textOldTunsi.length; count++)
        {
            char currentCharacter = this.textOldTunsi[count];
            char previousConsonant;
            int posPreviousConsonant = searchConsonant(count);
            boolean harderLetters = false;
            char previousCharacter = ' ';
            if(count> 0)
            {
                previousCharacter = this.textOldTunsi[count-1];
            }
            if(count > 0 && posPreviousConsonant !=-1)
            {
               previousConsonant = this.textOldTunsi[posPreviousConsonant];
               harderLetters = (previousConsonant == '\u062D') || (previousConsonant == '\u062E') ||
                        (previousConsonant == '\u0631') || (previousConsonant == '\u0635') ||
                        (previousConsonant == '\u0636') || (previousConsonant == '\u0637') ||
                        (previousConsonant == '\u0638') || (previousConsonant == '\u0639') ||
                        (previousConsonant == '\u063A') || (previousConsonant == '\u0642') ;
            }

            if(currentCharacter=='\u064E' && count>0)
            {
                if(harderLetters)
                {
                   this.textModernTunsi[countModernText]='a';
                }
                else
                {
                   this.textModernTunsi[countModernText]='e';
                }

            }
            else if(currentCharacter=='\u064F' && count>0)
            {
                if(harderLetters)
                {
                   this.textModernTunsi[countModernText]='o';
                }
                else
                {
                   this.textModernTunsi[countModernText]='u';
                }
            }
            else if(currentCharacter=='\u0650' && count>0)
            {
                if(harderLetters)
                {
                   this.textModernTunsi[countModernText]='\u0131';
                }
                else
                {
                   this.textModernTunsi[countModernText]='i';
                }
            }
            else if(currentCharacter=='\u0652' && count>0)
            {
               //skip
            }
            else if(currentCharacter=='\u0651' && count>0)
            {
                int consonant = searchConsonant(count);
                if(consonant != -1)
                {
                    if(consonant != (count-1))
                    {
                        this.textModernTunsi[countModernText]=this.textModernTunsi[countModernText-1];
                        this.textModernTunsi[countModernText-1]=this.textModernTunsi[consonant];
                    }
                    else
                    {
                        this.textModernTunsi[countModernText]=this.textModernTunsi[consonant];
                    }

                }
            }
            else if(this.modernTunsiLetters.containsKey(currentCharacter))
            {

               if(currentCharacter == '\u064A' && (count<this.textOldTunsi.length-1) && count>0)
               {
                   char charBefore = this.textModernTunsi[countModernText-1];
                   boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                           (charBefore == 'ê') || (charBefore == 'u') || (charBefore == 'û') || (charBefore == 'o') ||
                           (charBefore == 'ô');
                  if((this.textOldTunsi[count+1] != '\u064F' && this.textOldTunsi[count+1] != '\u064E' &&
                        this.textOldTunsi[count+1] != '\u0652' && this.textOldTunsi[count+1] != '\u0650' &&
                          this.textOldTunsi[count+1] != '\u0651') || (voyelBefore == false))
                  {
                      if(harderLetters)
                      {
                          this.textModernTunsi[countModernText] = '\u0131';
                          this.textModernTunsi = concatenate(this.textModernTunsi, 'î', count+1);
                          countModernText++;
                      }
                      else
                      {
                          this.textModernTunsi[countModernText] = 'î';
                      }
                  }
                  else
                  {
                      this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
                  }
               }
               else if(currentCharacter == '\u064A' && (count==this.textOldTunsi.length-1))
               {
                   char charBefore = this.textModernTunsi[countModernText-1];
                   boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                           (charBefore == 'ê') || (charBefore == 'u') || (charBefore == 'û') || (charBefore == 'o') ||
                           (charBefore == 'ô');
                   if(voyelBefore == false)
                   {
                       if(harderLetters)
                       {
                           this.textModernTunsi[countModernText] = '\u0131';
                           this.textModernTunsi = concatenate(this.textModernTunsi, 'î', count+1);
                           countModernText++;
                       }
                       else
                       {
                           this.textModernTunsi[countModernText] = 'î';
                       }
                   }
                   else
                   {
                       this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
                   }

               }
               else if((currentCharacter == '\u0648') && (previousCharacter == ' '))
               {
                   this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
               }
               else if(currentCharacter == '\u0648' && (count<this.textOldTunsi.length-1) && count>=0)
                {
                    char charBefore = this.textModernTunsi[countModernText-1];
                    boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                            (charBefore == 'ê') || (charBefore == 'i') || (charBefore == 'î') || (charBefore == '\u0131');

                    if((this.textOldTunsi[count+1] != '\u064F' && this.textOldTunsi[count+1] != '\u064E' &&
                            this.textOldTunsi[count+1] != '\u0652' && this.textOldTunsi[count+1] != '\u0650' &&
                            this.textOldTunsi[count+1] != '\u0651') || (voyelBefore == false))
                    {
                        if(harderLetters)
                        {
                            this.textModernTunsi[countModernText] = 'ô';
                        }
                        else
                        {
                            this.textModernTunsi[countModernText] = 'û';
                        }
                    }
                    else
                    {
                        this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
                    }
                }
                else if(currentCharacter == '\u0648' && (count==this.textOldTunsi.length-1))
                {
                    char charBefore = this.textModernTunsi[countModernText-1];
                    boolean voyelBefore = (charBefore == 'a') || (charBefore == 'â') || (charBefore == 'e') ||
                            (charBefore == 'ê') || (charBefore == 'i') || (charBefore == 'î') || (charBefore == '\u0131');
                    if(voyelBefore == false)
                    {
                        if(harderLetters)
                        {
                            this.textModernTunsi[countModernText] = 'ô';
                        }
                        else
                        {
                            this.textModernTunsi[countModernText] = 'û';
                        }
                    }
                    else
                    {
                        this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
                    }

                }
               else if(currentCharacter == '\u064F' && (count<this.textOldTunsi.length-1) && count>0)
               {

                   if((this.textOldTunsi[count+1] != '\u064F' && this.textOldTunsi[count+1] != '\u064E' &&
                           this.textOldTunsi[count+1] != '\u0652' && this.textOldTunsi[count+1] != '\u0650') &&
                           this.textOldTunsi[count+1] != '\u0651')
                   {
                       if(harderLetters)
                       {
                           this.textModernTunsi[countModernText] = 'o';
                           this.textModernTunsi = concatenate(this.textModernTunsi, 'u', count+1);
                           countModernText++;
                       }
                       else
                       {
                           this.textModernTunsi[countModernText] = 'û';
                       }

                   }
               }
               else if(currentCharacter == '\u064F' && (count==this.textOldTunsi.length-1))
               {
                   if(harderLetters)
                   {
                       this.textModernTunsi[countModernText] = 'o';
                       this.textModernTunsi = concatenate(this.textModernTunsi, 'u', count+1);
                       countModernText++;
                   }
                   else
                   {
                       this.textModernTunsi[countModernText] = 'û';
                   }
               }
               else if((currentCharacter == '\u0627' || currentCharacter == '\u0649') && harderLetters)
               {
                   this.textModernTunsi[countModernText] = 'â';
               }
               else if(currentCharacter == '\u0629' && (count< this.textOldTunsi.length-1) && this.textOldTunsi[count+1] == '\u0652')
               {
                   this.textModernTunsi[countModernText] = 't';
               }
               else
               {
                  this.textModernTunsi[countModernText]=this.modernTunsiLetters.get(currentCharacter);
               }
            }
            else
            {
              if(currentCharacter == '\u061F')
              {
                  this.textModernTunsi[countModernText]='?';
              }
              else if(currentCharacter == '\u060C')
              {
                 this.textModernTunsi[countModernText]=',';
              }
              else if(currentCharacter == '\u061B')
              {
                  this.textModernTunsi[countModernText]=';';
              }
              else
              {
                  this.textModernTunsi[countModernText]=currentCharacter;
              }
            }
            countModernText++;
        }
        String text = new String(this.textModernTunsi);
        if(text.contains("  "))
        {
            text = text.replace("  ", " ");
        }
        if(text.contains("aê"))
        {
            text = text.replace("aê", "ê");
        }
        if(text.contains("eu"))
        {
            text = text.replace("eu", "u");
        }
        /*delete redundancy*/
        if(text.contains("ee") || text.contains("eê") || text.contains("êe"))
        {
            text = text.replace("ee","ê");
            text = text.replace("eê","ê");
            text = text.replace("êe","ê");
        }
        if(text.contains("aa") || text.contains("aâ") || text.contains("âa"))
        {
            text = text.replace("aa","â");
            text = text.replace("aâ","â");
            text = text.replace("âa","â");
        }
        if(text.contains("ii") || text.contains("îi") || text.contains("iî") || text.contains("\u0131\u0131"))
        {
            text = text.replace("ii","î");
            text = text.replace("iî","î");
            text = text.replace("îi","î");
            text = text.replace("\u0131\u0131","\u0131");
        }
        if(text.contains("uu") || text.contains("uû") || text.contains("ûu"))
        {
            text = text.replace("uu","û");
            text = text.replace("uû","û");
            text = text.replace("ûu","û");
        }
        if(text.contains("oo") || text.contains("oô") || text.contains("ôo"))
        {
            text = text.replace("oo","ô");
            text = text.replace("oô","ô");
            text = text.replace("ôo","ô");
        }
        this.textModernTunsi = text.toCharArray();
    }

    public char[] concatenate(char[] text, char character, int position)
    {
        char[] newText = new char[text.length+1];
        for(int count=0; count<position; count++)
        {
            newText[count] = text[count];
        }
        newText[position] = character;
        return newText;
    }

    public char[] getTextModernTunsi()
    {
        return this.textModernTunsi;
    }

    public int searchConsonant(int current)
    {
        int consonant = -1;
        if(current != 0)
        {
            for(int count1=current; (count1 >=current-2) && ((current-2)>=0); count1--)
            {
                if(this.modernTunsiLetters.containsKey(textOldTunsi[count1]) && textOldTunsi[count1]!='\u0627' &&
                textOldTunsi[count1]!='\u0649')
                {
                    consonant=count1;
                    break;
                }

            }
        }
        return consonant;
    }

    public int[] searchVoyels(int current)
    {
        int length = this.textOldTunsi.length;
        int[] voyels = new int[2];
        if(current == 0)
        {
            voyels[0] = length;
        }
        else if(current==this.textOldTunsi[length-1])
        {
            voyels[1] = length;
        }
        else
        {
            for(int count1=current; (count1 >current-2) && ((current-2)>=0); count1--)
            {
                 if((this.textOldTunsi[count1] == '\u064F' || this.textOldTunsi[count1] == '\u064E' ||
                         this.textOldTunsi[count1] == '\u0652' || this.textOldTunsi[count1] == '\u0650'))
                 {
                     voyels[0]=count1;
                 }
            }
            for(int count2=current; (count2 <current+2) && ((current+2)<length); count2++)
            {
                if((this.textOldTunsi[count2] == '\u064F' || this.textOldTunsi[count2] == '\u064E' ||
                        this.textOldTunsi[count2] == '\u0652' || this.textOldTunsi[count2] == '\u0650'))
                {
                    voyels[1]=count2;
                }
            }
        }
        return voyels;
    }



}