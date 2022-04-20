using TechWorld_Xamarin.Model;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class OrderDetail : ContentPage
    {
        public TechWorld_Xamarin.Model.Ordine myOrder;

        public OrderDetail(TechWorld_Xamarin.Model.Ordine order)
        {
            myOrder = order;
            InitializeComponent();
            show();
            showOrderProduct.ItemsSource = myOrder.listaProdotti; 

            showOrderProduct.ItemTapped += async (o, e) =>
            {
                var myList = (ListView)o;
                myList.SelectedItem = null;
            };
        }

        private void show()
        {
            Label_citta.Text = myOrder.citta;
            Label_nr.Text = myOrder.nr.ToString();
            Label_title.Text = "Ecco i dettagli relativi all'ordine con id: "+myOrder.id.ToString() +" acquistato in data: "+string.Format("{0:yyyy, MMMM dd}",myOrder.data);
            Label_via.Text = myOrder.via;
            Label_cost.Text = cost();
        }

        private string cost()
        {
            double x = 0;
            foreach(Prodotto p in myOrder.listaProdotti)
            {
                x += p.prezzo;
            }
            return x.ToString()+"$";
        }
    }
}