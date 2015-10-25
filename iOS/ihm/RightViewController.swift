import UIKit

class RightViewController : UIViewController, UITableViewDataSource, UITableViewDelegate, UIAlertViewDelegate {
    
    var listMessage: NSDictionary = NSDictionary()
    var mensagem: NSString = NSString()
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tableView.delegate = self
        self.tableView.tableFooterView = UIView(frame: CGRectZero)
    }
    override func viewWillAppear(animated: Bool) {
        self.tableView.reloadData()
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return mensagens.count
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCellWithIdentifier("todoCell", forIndexPath: indexPath) 
        
        if let valor = mensagens[indexPath.row].valueForKey("Mensagem") as? String{
            cell.textLabel?.text = valor
        }
		
        return cell
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        let alert = UIAlertView()
        alert.title = "Atenção"
        alert.message = (mensagens[indexPath.row].valueForKey("Mensagem") as! String)
        alert.addButtonWithTitle("OK")
        alert.show()
    }
}
