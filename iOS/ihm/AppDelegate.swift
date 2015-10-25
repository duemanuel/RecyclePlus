//
//  AppDelegate.swift
//  ihm
//
//  Created by Eduardo Oliveira on 07/10/15.
//  Copyright © 2015 IHM. All rights reserved.
//

import UIKit
import CoreData

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
	var timer: NSTimer!


    func application(application: UIApplication, didFinishLaunchingWithOptions launchOptions: [NSObject: AnyObject]?) -> Bool {
        // Override point for customization after application launch.
        
        Parse.setApplicationId("zNtT8LfCaZJsXdeeZEeHRb44jX0nlesKT4x810d3", clientKey: "cAhN7GDJrSvVQEiY9n8c0KTEGmnqBdTJx1M8X7Q5")
        
//      let settings = UIUserNotificationSettings(forTypes: [.Alert, .Badge, .Sound], categories: nil)
//      UIApplication.sharedApplication().registerUserNotificationSettings(settings)
		
		var storyboard = UIStoryboard(name: "Main", bundle: nil)
		let loginViewController = storyboard.instantiateViewControllerWithIdentifier("LoginViewController") as! LoginViewController
		
		self.window?.rootViewController = loginViewController
		
        return true
    }
    
    //Pede permissão ao usuario para utilizar as notificações
    func application(application: UIApplication, didRegisterUserNotificationSettings notificationSettings: UIUserNotificationSettings) {
        _ = notificationSettings.types
        UIApplication.sharedApplication().registerForRemoteNotifications()
    }
    
    
    //Função chamada no inicio da aplicação
    func askNotificationsPermissions(){
        if(UIApplication.instancesRespondToSelector(Selector("registerUserNotificationSettings:"))){
            let settings = UIUserNotificationSettings(forTypes: [.Alert, .Badge, .Sound], categories: nil)
            UIApplication.sharedApplication().registerUserNotificationSettings(settings)
        }
    }
    
    //Valida o token instalado
    func application(application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: NSData) {
        let currentInstallation = PFInstallation.currentInstallation()
        currentInstallation.setDeviceTokenFromData(deviceToken)
		
        if channelRemove != ""{
            var channels = [String]()
            if currentInstallation.channels != nil{
                channels = currentInstallation.channels as! [String]
                if(channels.count > 0){
                    for(var i = 0; i < channels.count; i++){
                        if channelRemove != ""{
                            if(channels[i] == channelRemove){
                                currentInstallation.removeObject(channelRemove, forKey: "channels")
                            }
                        }
                    }
                }
            }
		}
		else{
			currentInstallation.addUniqueObject(channel, forKey: "channels")
		}
        currentInstallation.saveInBackground()
		
		timer = NSTimer.scheduledTimerWithTimeInterval(3, target: self, selector: "updateChannel", userInfo: nil, repeats: true)
    }
	
	func updateChannel(){
		let currentInstallation = PFInstallation.currentInstallation()
		var channels = [String]()
        if currentInstallation.channels != nil{
            channels = currentInstallation.channels as! [String]
            if(channels.count == 0){
                currentInstallation.addUniqueObject(channel, forKey: "channels")
                currentInstallation.saveInBackground()
            }
        }else{
            currentInstallation.addUniqueObject(channel, forKey: "channels")
        }
		timer.invalidate()
	}
	
    //Metodo se o token estiver incoreto
    func application(application: UIApplication, didFailToRegisterForRemoteNotificationsWithError error: NSError) {
        print("Falha ao gerar token", error)
    }
    
    //Sucesso na notificação
    func application(application: UIApplication, didReceiveRemoteNotification userInfo: [NSObject : AnyObject]) {
		print(userInfo)
    }
    
    //Metodo chamado independentemente do estado do app.
    func application(application: UIApplication, didReceiveRemoteNotification userInfo: [NSObject : AnyObject], fetchCompletionHandler completionHandler: (UIBackgroundFetchResult) -> Void) {
        print("Notificacao Silenciosa")
        completionHandler(UIBackgroundFetchResult.NewData)
    }

    func applicationWillResignActive(application: UIApplication) {

        // Sent when the application is about to move from active to inactive state. This can occur for certain types of temporary interruptions (such as an incoming phone call or SMS message) or when the user quits the application and it begins the transition to the background state.
        // Use this method to pause ongoing tasks, disable timers, and throttle down OpenGL ES frame rates. Games should use this method to pause the game.
    }

    func applicationDidEnterBackground(application: UIApplication) {

        // Use this method to release shared resources, save user data, invalidate timers, and store enough application state information to restore your application to its current state in case it is terminated later.
        // If your application supports background execution, this method is called instead of applicationWillTerminate: when the user quits.
    }

    func applicationWillEnterForeground(application: UIApplication) {

        // Called as part of the transition from the background to the inactive state; here you can undo many of the changes made on entering the background.
    }

    func applicationDidBecomeActive(application: UIApplication) {
        // Restart any tasks that were paused (or not yet started) while the application was inactive. If the application was previously in the background, optionally refresh the user interface.
        
        let currentInstallation = PFInstallation.currentInstallation()
        if currentInstallation.badge != 0{
            currentInstallation.badge = 0
            currentInstallation.saveEventually()
        }
    }

    func applicationWillTerminate(application: UIApplication) {
        // Called when the application is about to terminate. Save data if appropriate. See also applicationDidEnterBackground:.        
    }
}


