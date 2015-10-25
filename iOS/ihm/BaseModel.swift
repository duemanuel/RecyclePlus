//
//  BaseModel.swift
//  ihm
//
//  Created by Eduardo Oliveira on 07/10/15.
//  Copyright © 2015 IHM. All rights reserved.
//

import UIKit
import CoreData

var channel: String = String()
var channelRemove: String = String()
var loginTXT: String = String()
var senhaTXT: String = String()
var permissaoTXT: String = String()

var urlBase: String = "http://10.1.32.11/CSAMobile/"
var urlLogin: String = "CSAMobile.svc/Login"
var urlMensagens: String = "CSAMobile.svc/Alertas/"

var mensagens: [NSDictionary] = [NSDictionary]()

public class BaseModel: NSObject {
    
    enum InputError: ErrorType{
        case InputMissing
        case AgeIncorret
    }
    
    public func recuperarMensagens(permissao: String){
        let url = urlBase + urlMensagens + permissao
        let sessionConfig = NSURLSessionConfiguration.defaultSessionConfiguration()
        let session = NSURLSession(configuration: sessionConfig)
        let request = NSMutableURLRequest(URL: NSURL(string: url)!)
        
        request.HTTPMethod = "GET"
        request.addValue("application/json", forHTTPHeaderField: "Content-Type")
        request.addValue("application/json", forHTTPHeaderField: "Accept")
        
        let task = session.dataTaskWithRequest(request, completionHandler: {data, response, error -> Void in
            guard data != nil else {
                print("no data found: \(error)")
                return
            }
            do {
                if let json = try NSJSONSerialization.JSONObjectWithData(data!, options: []) as? [NSDictionary] {
                    mensagens = json
                }
            }
            catch let parseError {
                print(parseError)
                let jsonStr = NSString(data: data!, encoding: NSUTF8StringEncoding)
                print("Error could not parse JSON: '\(jsonStr)'")
            }
        })
        task.resume()
    }
}
