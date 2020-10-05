public class Tunsi
{
    public static void main(String[] args)
    {
        TweetTunsi tweet = new TweetTunsi();
        tweet.setTweetOldTunsi("اُمّي سيسي تُكنِس تُكنِس لقات فلَيّس قالت: \"آش نِشري بيه؟ آش نِشري بيه؟ زَعمَة نِشري قَدّيد نطَيِّب كُسكسي بنين لبنَيّتي؟\".");
        tweet.setTweetModernTunsi();
        String t = "سِّ";
        char [] array = t.toCharArray();
        System.out.println(tweet.getTweetModernTunsi());
        /**
         * عَسلَامَه
         *
         * */
    }
}
