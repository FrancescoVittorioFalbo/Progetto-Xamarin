using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DettagliUtente : ContentPage
    {
        public List<TechWorld_Xamarin.Model.Ordine> ordiniEffettuati;
        public TechWorld_Xamarin.Services.ShopMoneteServices shop;

        public DettagliUtente()
        {
            InitializeComponent();
            aggiornaInfo();
            show();
            shop = new Services.ShopMoneteServices();
        }

        private void show()
        {
            Label_title.Text = "Account di - " + (App.Current as App).userLoggato.username;
            Label_cognome.Text = (App.Current as App).userLoggato.cognome;
            Label_coin.Text = (App.Current as App).userLoggato.coin.ToString() + " monete";
            Label_email.Text = (App.Current as App).userLoggato.email;
            Label_phone.Text = (App.Current as App).userLoggato.cell.ToString();
            Label_nome.Text = (App.Current as App).userLoggato.nome;

        }

        public void shop50(object sender, EventArgs e)
        {
            var clienteAggiornato = shop.shopCoin(50, (App.Current as App).userLoggato).Result;
            (App.Current as App).userLoggato = clienteAggiornato;
            show();
        }

        public void shop100(object sender, EventArgs e)
        {
            var clienteAggiornato = shop.shopCoin(100, (App.Current as App).userLoggato).Result;
            (App.Current as App).userLoggato = clienteAggiornato;
            show();
        }

        public void shop200(object sender, EventArgs e)
        {
            var clienteAggiornato = shop.shopCoin(200, (App.Current as App).userLoggato).Result;
            (App.Current as App).userLoggato = clienteAggiornato;
            show();
        }

        public void shop500(object sender, EventArgs e)
        {
            var clienteAggiornato = shop.shopCoin(500, (App.Current as App).userLoggato).Result;
            (App.Current as App).userLoggato = clienteAggiornato;
            show();
        }

        public async void aggiornaInfo()
        {
            while (true)
            {
                await Task.Delay(5000);
                show();
            }
        }

        public async void logout(object sender, EventArgs e)
        {
            var action = await DisplayAlert("Exit?", "Sicuro di voler effettuare il logout?", "Yes", "No");
            if (action)
            {
                App.Current.MainPage = new NavigationPage(new LoginPage());
            }
        }

        public async void viewOrder(object sender, EventArgs e)
        {
            await Navigation.PushModalAsync(new NavigationPage(new ListaOrdini()));
        }
    }
}