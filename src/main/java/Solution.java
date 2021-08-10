public class Solution {

    public static String generate_bc(String url, String separator) {

        String ignoredWords = "the, of, in, from, by, with, and, or, for, to, at, a";
        StringBuilder result = new StringBuilder();

        String[] words = url.split("/");

        String lastWord = "";
        for (int i = 0; i < words.length; i++) {

            if (words[i].contains("?") || words[i].contains("#")) {
                words[i] = words[i].replaceAll("\\?[=a-zA-Z]*", "").replaceAll("#[=a-zA-Z]*", "");
            }

            if (i == 0) {
                result.append("<a href=\"/\">HOME</a>");
                continue;
            }

            if (i == words.length - 1) {
                if (words[i].startsWith("index.")) {
                    lastWord = lastWord.replaceAll("href=\"/[a-zA-Z]*/\">", "class=\"active\">").replace("<a", "<span").replace("</a>", "</span>");
                    result.append(lastWord);
                    lastWord = "";
                } else {
                    result.append(lastWord);
                    result.append(separator)
                            .append("<span class=\"active\">")
                            .append(words[i].replaceAll("\\.[a-zA-Z]*", "").replace("-", " ").toUpperCase())
                            .append("</span>");
                }
                continue;
            }

            result.append(lastWord);
            lastWord = "";

            if (words[i].length() < 30) {
                lastWord = separator +
                        "<a href=\"/" + words[i] + "/" + "\">" +
                        words[i].replace("-", " ").toUpperCase() +
                        "</a>";
            } else {
                String[] splitWord = words[i].split("-");

                lastWord = separator + "<a href=\"/" + words[i] + "/" + "\">";

                for (String str : splitWord) {
                    if (!ignoredWords.contains(str)) {
                        lastWord += str.substring(0, 1).toUpperCase();
                    }
                }

                lastWord += "</a>";
            }
        }

        return result.toString();
    }
}