package utils;

public abstract class StringUtils {

    public static String buildLine(String[] infos){
        StringBuilder stringBuilder = new StringBuilder();
        String separator = " - ";

        for(int nbInfo = 0; nbInfo < infos.length; nbInfo++){
            if(!infos[nbInfo].equals(null)){
                stringBuilder.append(infos[nbInfo]);
            }
            if(nbInfo < infos.length - 1){
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }

}
