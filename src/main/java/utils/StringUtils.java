package utils;

public abstract class StringUtils {

    public static String buildLine(String[] infos){
        StringBuilder stringBuilder = new StringBuilder();
        String separator = " - ";

        for(String info: infos){
            if(!info.equals(null)){
                stringBuilder.append(info);
            }
            if(!info.equals(infos[infos.length-1])){
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }

    public static String buildEntireOutput(){

    }

}
