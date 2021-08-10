public class Solution {

    public static String generate_bc(String url, String separator) {

        url = url.replaceAll("https?://","")
                .replaceAll("[?#][=a-zA-Z_&]*", "")
                .replaceAll("\\.[a-zA-Z]+$", "")
                .replaceAll("/index$", "");

        String ignoredWords = "the, of, in, from, by, with, and, or, for, to, at, a";
        StringBuilder result = new StringBuilder();
        String currentPath = "";
        String[] words = url.split("/");

        for (int i = 0; i < words.length; i++) {

            if (words.length == 1) {
                result.append("<span class=\"active\">HOME</span>");
                break;
            }

            if (i == 0) {
                result.append("<a href=\"/\">HOME</a>");
                continue;
            }

            if (i == words.length - 1) {

                    if (words[i].length() < 31) {
                        result.append(separator)
                                .append("<span class=\"active\">")
                                .append(words[i].replace("-", " ").toUpperCase())
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
                continue;
            }

            currentPath += words[i] + "/";

            if (words[i].length() < 31) {
                result.append(separator)
                        .append("<a href=\"/")
                        .append(currentPath)
                        .append("\">")
                        .append(words[i].replace("-", " ").toUpperCase())
                        .append("</a>");
            } else {
                String[] splitWord = words[i].split("-");
                result.append(separator)
                        .append("<a href=\"/")
                        .append(currentPath)
                        .append("\">");

                for (String str : splitWord) {
                    if (!ignoredWords.contains(str)) {
                        result.append(str.substring(0, 1).toUpperCase());
                    }
                }
                result.append("</a>");
            }
        }
        return result.toString();
    }
}