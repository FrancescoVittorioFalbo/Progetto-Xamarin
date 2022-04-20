using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.Services;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ListaOrdini : ContentPage
    {
        public LoginService extract;
        public ObservableCollection<Ordine> Lista = new ObservableCollection<Ordine>();

        public ListaOrdini()
        {
            BindingContext = this;
            extract = new LoginService();
            Lista = extract.RicavaOrdini((App.Current as App).userLoggato.username).Result;

            InitializeComponent();

            ShowOrder.ItemsSource = Lista.Reverse();

            ShowOrder.ItemTapped += async (o, e) =>
            {
                var myList = (ListView)o;
                var myOrdine = (myList.SelectedItem as Ordine);
                await Navigation.PushModalAsync(new NavigationPage(new OrderDetail(myOrdine)));
                myList.SelectedItem = null;
            };

            check();
            NavigationPage.SetHasNavigationBar(this, false);
        }

        public async void check()
        {
            while (true)
            {
                await Task.Delay(3000);
                Lista = extract.RicavaOrdini((App.Current as App).userLoggato.username).Result;
                ShowOrder.ItemsSource = Lista;
            }
        }
    }
}