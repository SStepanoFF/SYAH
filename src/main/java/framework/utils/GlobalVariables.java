package framework.utils;

public class GlobalVariables {

    public static int GlobalTimeOut=Integer.parseInt(Loader.loadProperty("timeout"));
    public static String DownloadExcelFilePath =System.getProperty("user.home") + "\\Downloads\\ContactsOfGroup-Excel.xls";
    public static String AutofillCSVFilePath =System.getProperty("user.dir")+"\\src\\main\\resources\\HAYSAutofill.csv";

    public static String PortalUrl= Loader.loadProperty("portalUrl");
    public static String getPortalLogin(){
        return Loader.loadProperty("portalLogin");
    }
    public static String getPortalPass(){
        return Loader.loadProperty("portalPass");
    }

    public static String KSUrl=Loader.loadProperty("KSUrl");
    public static String KSUsername=Loader.loadProperty("KSlogin");
    public static String KSUserPass= Loader.loadProperty("KSpass");
    public static String KSSurveyID = Loader.loadProperty("SID");

//    private static String SurveyUrl;
    public static String getSurveyUrl() {
        return Loader.loadProperty("SurveyUrl");
    }
    public static void setSurveyUrl(String url) {
        Loader.updateProperty("SurveyUrl", url);
    }

    public static String getSurveyID() {
        return Loader.loadProperty("SurveyID");
    }
    public static void setSurveyID(String surveyID) {
        Loader.updateProperty("SurveyID", surveyID);
    }

    public static String getRespondentID(){
        return Loader.loadProperty("respondentID");
    }
    public static void setRespondentID(String respondID){
        Loader.updateProperty("respondentID", respondID);
    }

    public static String getAlertType() {
        return Loader.loadProperty("alertType").toLowerCase();
    }
    public static void setAlertType(String alertType) {
        Loader.updateProperty("alertType",alertType);
    }

    public static String getAlertStatus() {
        return Loader.loadProperty("alertStatus");
    }
    public static void setAlertStatus(String alertStatus) {
        Loader.updateProperty("alertStatus",alertStatus);
    }


}
