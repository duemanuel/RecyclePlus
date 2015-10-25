//
//  ViewController.swift
//  ihm
//
//  Created by Eduardo Oliveira on 07/10/15.
//  Copyright © 2015 IHM. All rights reserved.
//

import UIKit

enum InputError: ErrorType{
	case InputMissing
	case AgeIncorret
}

public class LoginViewController: UIViewController{

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
        
//        months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
//        let unitsSold = [20.0, 4.0, 6.0, 3.0, 12.0, 16.0, 4.0, 18.0, 2.0, 4.0, 5.0, 4.0]
//
//        setChart(months, values: unitsSold)
    }
}
	
//    func setChart(dataPoints: [String], values: [Double]){
//        barChartView.noDataText = "Voce precisa de dados para o grafico."
//
//        var dataEntries: [BarChartDataEntry] = []
//        
//        for i in 0..<dataPoints.count {
//            let dataEntry = BarChartDataEntry(value: values[i], xIndex: i)
//            dataEntries.append(dataEntry)
//        }
//        
//        let chartDataSet = BarChartDataSet(yVals: dataEntries, label: "Units Sold")
//        let chartData = BarChartData(xVals: months, dataSet: chartDataSet)
//        barChartView.data = chartData
//    }