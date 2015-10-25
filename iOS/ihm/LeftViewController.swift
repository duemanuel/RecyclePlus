import UIKit

enum LeftMenu: Int {
    case Reciclar = 0
    case Reciclados
    case Logout
    
}

protocol LeftMenuProtocol : class {
    func changeViewController(menu: LeftMenu)
    
}

class LeftViewController : UIViewController, LeftMenuProtocol {
    @IBOutlet weak var tableView: UITableView!
    var menus = ["Reciclar", "Reciclados", "Logout"]
    var mainViewController: UIViewController!
    var carregaViewController: UIViewController!
    var fornoViewController: UIViewController!
    var corridaViewController: UIViewController!
    var monitViewController: UIViewController!
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
        
    }
    
    override func viewDidLoad() {
        
        super.viewDidLoad()
        //self.tableView.separatorColor = UIColor(red: 224/255, green: 224/255, blue: 224/255, alpha: 1.0)
        self.tableView.tableFooterView = UIView(frame: CGRectZero)
        //var storyboard = UIStoryboard(name: "Main", bundle: nil)
        //let carregaViewController = storyboard.instantiateViewControllerWithIdentifier("tabelaSB") as! TabelaViewController
        //self.carregaViewController = UINavigationController(rootViewController: carregaViewController)
        //
        //let fornoViewController = storyboard.instantiateViewControllerWithIdentifier("FornoViewController") as! FornoViewController
        //self.fornoViewController = UINavigationController(rootViewController: fornoViewController)
        //
        //let corridaViewController = storyboard.instantiateViewControllerWithIdentifier("CorridaViewController") as! CorridaViewController
        //self.corridaViewController = UINavigationController(rootViewController: corridaViewController)
        //
        //let monitViewController = storyboard.instantiateViewControllerWithIdentifier("MonitViewController") as! MonitViewController
        //self.monitViewController = UINavigationController(rootViewController: monitViewController)
        //
        //self.tableView.registerCellClass(BaseTableViewCell.self)
        
    }
    
    override func viewDidAppear(animated: Bool) {
        super.viewDidAppear(animated)
        
    }
    
    func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return menus.count
        
    }
    
    func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
		
		let cell = tableView.dequeueReusableCellWithIdentifier("cell")
		
		cell?.textLabel?.text = menus[indexPath.row]
        return cell!
        
    }
    
    func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
        //if let menu = LeftMenu(rawValue: indexPath.item) {
            //self.changeViewController(menu)
        //}
        
    }
    
    func changeViewController(menu: LeftMenu) {
        switch menu {
        case .Reciclar:
            self.slideMenuController()?.changeMainViewController(self.mainViewController, close: true)
        case .Reciclados:
            self.slideMenuController()?.changeMainViewController(self.carregaViewController, close: true)
            break
        case .Logout:
            self.slideMenuController()?.changeMainViewController(self.fornoViewController, close: true)
            break
        default:
            break
        }
    }
}