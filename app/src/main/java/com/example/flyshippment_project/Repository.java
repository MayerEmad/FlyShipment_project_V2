package com.example.flyshippment_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.webkit.URLUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import APIs.ApiRejester;
import APIs.ApiRequests;
import APIs.ApiShipmentSearch;
import APIs.ApiTripNav;
import APIs.ApiTripSearch;
import APIs.ApiUserInfo;
import APIs.ApiShipmentNav;
import adapters_and_items.ProfileItem;
import adapters_and_items.ShipmentDealItem;
import adapters_and_items.ShipmentItem;
import adapters_and_items.ShipmentRequestItem;
import adapters_and_items.TripItem;

public class Repository
{
    public static Context APPContext=null;


    public static boolean completeProfileData(){
        ProfileItem user=Repository.TheProfileItem;
        return user.getUser_phone()!=null && !user.getUser_phone().equals("required") &&
               user.getUser_passport()!=null && !user.getUser_passport().equals("required");
    }
    public static boolean validPhone(String phoneNum){
        return phoneNum.matches("^[0-9]{10,13}$");
    }
    public static boolean validPassport(String passport){
        return passport.matches("^(?!^0+$)[a-zA-Z0-9]{3,20}$");
    }
    public static boolean validUrl(String url){
        return URLUtil.isValidUrl(url);
    }


    //--------------------UserInfo-------------------------

    public static ProfileItem TheProfileItem=null;

    public static void getUserInfo(int id,Context appCon){
        ApiUserInfo task=new ApiUserInfo();
        task.GetUserInfoFromApi(id,appCon);
    }

    public static void updateUserInfo(Context EditProfilePageContext,boolean imageEdited,Context appCon) {
        ApiUserInfo task=new ApiUserInfo();
        if(imageEdited)
            task.UpdateUserInfoApi(EditProfilePageContext,appCon);
        else
            task.UpdateUserInfoApiNoImage(EditProfilePageContext,appCon);
    }

    //------------------------shipments-------------------------

    public static ArrayList<ShipmentItem> getShipmentsFromApi(Context appCon) {
        ApiShipmentSearch task=new ApiShipmentSearch();
        task.GetShipmentItemsFromServer(appCon); //  Log.i("Repository getShips", "------> getting data from server...");
        return MyViewModel.getShipmentLiveData().getValue();
    }

    public static void uploadShipmentItem(ShipmentItem item, Context CreateShipmentItemActivityContext,Context appCon)
    {
        ApiShipmentSearch task=new ApiShipmentSearch();
        task.UploadShipmentItem(item,CreateShipmentItemActivityContext,appCon);
    }
     public static void updateShipmentItem(ShipmentItem item, Context EditShipmentItemActivityContext,boolean imageEdited,Context appCon)
    {
        ApiShipmentSearch task=new ApiShipmentSearch();
        if(imageEdited)
            task.UpdateShipmentItem(item,EditShipmentItemActivityContext,appCon);
        else
            task.UpdateShipmentItemNoImage(item,EditShipmentItemActivityContext);//update but not the image
    }
    public static void deleteShipmentItem(Integer id)
    {
        ApiShipmentSearch task=new ApiShipmentSearch();
        task.DeleteShipmentItem(id);
    }
    public static ArrayList<ShipmentItem> getUserShipmentsFromApi()
    {
        ApiShipmentNav task=new ApiShipmentNav();
        task.GetUserShipmentFromServer(); //  Log.i("Repository getShips", "------> getting data from server...");
        return MyViewModel.getUserShipmentLiveData().getValue();
    }

    //-------------------------Trips-----------------------

    public static ArrayList<TripItem> getTripsFromApi()
    {
        ApiTripSearch task=new ApiTripSearch();
        task.GetTripItemsFromServer();
        return MyViewModel.getTripLiveData().getValue();
    }
    public static void uploadTripItem(TripItem item)
    {
        ApiTripSearch task=new ApiTripSearch();
        task.UploadTripItem(item);
    }
    public static void updateTripItem(TripItem item)
    {
        ApiTripSearch task=new ApiTripSearch();
        task.UpdateTripItem(item);
    }
    public static void deleteTripItem(Integer id)
    {
        ApiTripSearch task=new ApiTripSearch();
        task.DeleteTripItem(id);
    }
    public static ArrayList<TripItem> getUserTripsFromApi()
    {
        ApiTripNav task=new ApiTripNav();
        task.GetUserTripItemsFromServer();
        return  MyViewModel.getUserTripLiveData().getValue();
    }

    //-----------------------requests----------------------------
    public static void sendRequestForTrip(int shipment_id, int tripId) {
        ApiRequests task=new ApiRequests();
        task.ShipmentAskForTrip(shipment_id,tripId);
    }

    public static ArrayList<ShipmentRequestItem> getShipmentRequestsFromApi() {
        ApiRequests task=new ApiRequests();
        task.GetShipmentsRequests();
        return MyViewModel.getShipmentRequestsLiveData().getValue();
    }

    public static void approveShipmentRequest(int request_id) {
        ApiRequests task=new ApiRequests();
        task.ApproveShipmentRequest(request_id);
    }

    public static void rejectShipmentRequest(int request_id) {
        ApiRequests task=new ApiRequests();
        task.RejectShipmentRequest(request_id);
    }

    public static void afterRejectShipmentRequest(int request_id) {
        ApiRequests task=new ApiRequests();
        task.AfterRejectShipmentRequest(request_id);
    }

    public static ArrayList<ShipmentDealItem> getShipmentDealsFromApi() {
        ApiRequests task=new ApiRequests();
        task.GetShipmentDeals();
        return MyViewModel.getShipmentDealsLiveData().getValue();
    }

    public static void moveStepShipmentDealPath(int deal_id,Context appCon) {
        ApiRequests task=new ApiRequests();
        task.MoveStepShipmentDealPath(deal_id,appCon);
    }

    public static void sendShipmentDealRate(float rate, int user_id) {
        ApiRequests task=new ApiRequests();
        task.SendShipmentDealRate(rate,user_id);
    }

    //-----------------------Rejester---------------------
    public static void startRejestering(Context appCon,String a, String b,String c,String d){
        ApiRejester task=new ApiRejester();
        task.StartRejestering(appCon,a,b,c,d);
    }
}

