using System;
using System.Collections.ObjectModel;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.Services;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ListaProdotti : ContentPage
    {
        string selected;
        public Prodotto_CategoriaServices show;
        public ObservableCollection<Prodotto> Lista = new ObservableCollection<Prodotto>();

        public ListaProdotti()
        {
            BindingContext = this;
            show = new Prodotto_CategoriaServices();

            InitializeComponent();

            selected = "Schede Video";
            Lista = show.changeCategoria(selected).Result;
            ShowProduct.ItemsSource = Lista;

            ShowProduct.ItemTapped += async (o, e) =>
            {
                var myList = (ListView)o;
                var myProduct = (myList.SelectedItem as Prodotto);
                await Navigation.PushModalAsync(new NavigationPage(new DetailPage(myProduct)));
                myList.SelectedItem = null;
            };

            NavigationPage.SetHasNavigationBar(this, false);
        }

        public async void changeSelected(object sender, EventArgs e)
        {
            selected = ScegliCategoria.SelectedItem.ToString();
            var nowList = show.changeCategoria(selected).Result;
            ShowProduct.ItemsSource = nowList;
        }

        public async void check()
        {
            while (true)
            {
                await Task.Delay(3000);
                Lista = show.changeCategoria(selected).Result;
                ShowProduct.ItemsSource = Lista;
            }
        }
    }
}