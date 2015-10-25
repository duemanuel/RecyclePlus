//
//  ViewController.swift
//  ihm
//
//  Created by Eduardo Oliveira on 07/10/15.
//  Copyright © 2015 IHM. All rights reserved.
//

import UIKit
import AddressBook
import MediaPlayer
import CoreMotion
import CoreLocation

enum InputError: ErrorType{
	case InputMissing
	case AgeIncorret
}

public class LoginViewController: UIViewController, GPPSignInDelegate{

    //@IBOutlet weak var barChartView: BarChartView!
    @IBOutlet weak var loginTF: UITextField!
    @IBOutlet weak var senhaTF: UITextField!
	
	var resultado: NSDictionary = NSDictionary()
    var baseModel = BaseModel()
	
	public func createMenuView() {
		
		// create viewController code...
		let storyboard = UIStoryboard(name: "Main", bundle: nil)
		
		let mainViewController = storyboard.instantiateViewControllerWithIdentifier("MainViewController") as! MainViewController
		let leftViewController = storyboard.instantiateViewControllerWithIdentifier("LeftViewController") as! LeftViewController
		let rightViewController = storyboard.instantiateViewControllerWithIdentifier("RightViewController") as! RightViewController
		
		let nvc: UINavigationController = UINavigationController(rootViewController: mainViewController)
		
		leftViewController.mainViewController = nvc
		
		let slideMenuController = SlideMenuController(mainViewController:nvc, leftMenuViewController: leftViewController, rightMenuViewController: rightViewController)
		
		self.presentViewController(slideMenuController, animated: true, completion: nil)
	}
    
	@IBAction func loginBTN(sender: AnyObject) {
		do{
			let login = try presentPermissions()
			print("Resultado \(login)")
		} catch InputError.InputMissing{
			print("InputMissing")
		} catch InputError.AgeIncorret{
			print("AgeIncorret")
		} catch{
			print("Tente Novamente")
		}
	}
    
    func presentPermissions() throws -> Void{
		
		createMenuView()
    }
    
    override public func viewDidLoad() {
        super.viewDidLoad()
		
		var signIn = GPPSignIn?()
		
		//Configura Google Login
		signIn = GPPSignIn.sharedInstance()
		signIn?.shouldFetchGooglePlusUser = true
		signIn?.clientID = "191829872646-v62nknubeau31o106ilb6v8198kan300.apps.googleusercontent.com"
		signIn?.scopes = [kGTLAuthScopePlusLogin]
		signIn?.delegate = self
		signIn?.authenticate()
    }
	
	public func finishedWithAuth(auth: GTMOAuth2Authentication!, error: NSError!) {
		print(auth)
	}
	
	public func didDisconnectWithError(error: NSError!) {
		
	}
}