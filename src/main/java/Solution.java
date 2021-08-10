public class Solution {

    public static String generate_bc(String url, String separator) {

        if (url.startsWith("http")) {
            url = url.replaceAll("https?://","");
        }

        String ignoredWords = "the, of, in, from, by, with, and, or, for, to, at, a";
        StringBuilder result = new StringBuilder();
        String currentPath = "";
        String[] words = url.split("/");
        String lastWord = "";

        for (int i = 0; i < words.length; i++) {

            if (words[i].contains("?") || words[i].contains("#")) {
                words[i] = words[i].replaceAll("\\?[=a-zA-Z_&]*", "").replaceAll("#[=a-zA-Z_&]*", "");
            }

            if (words.length == 1) {
                result.append("<span class=\"active\">HOME</span>");
                continue;
            }

            if (i == 0) {
                lastWord += ("<a href=\"/\">HOME</a>");
                continue;
            }

            if (i == words.length - 1) {

                if (words[i].startsWith("index.") || words[i].equals("")) {
                    lastWord = lastWord.replaceAll("href=\"/?[/a-zA-Z\\-]*/?\">", "class=\"active\">").replace("<a", "<span").replace("</a>", "</span>");
                    result.append(lastWord);
                    lastWord = "";
                } else {
                    result.append(lastWord);

                    if (words[i].length() < 31) {
                        result.append(separator)
                                .append("<span class=\"active\">")
                                .append(words[i].replaceAll("\\.[a-zA-Z]*", "").replace("-", " ").toUpperCase())
                                .append("</span>");
                    } else {
                        String[] splitWord = words[i].split("-");
                        result.append(separator)
                                .append("<span class=\"active\">");

                        for (String str : splitWord) {
                            if (!ignoredWords.contains(str)) {
                                result.append(str.substring(0, 1).toUpperCase());
                            }
                        }
                        result.append("</span>");
                    }
                }
                continue;
            }

            result.append(lastWord);
            lastWord = "";
            currentPath += words[i] + "/";

            if (words[i].length() < 31) {
                lastWord = separator +
                        "<a href=\"/" + currentPath + "\">" +
                        words[i].replace("-", " ").toUpperCase() +
                        "</a>";
            } else {
                String[] splitWord = words[i].split("-");
                lastWord = separator + "<a href=\"/" + currentPath + "\">";

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