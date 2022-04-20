using System;
using System.Collections.ObjectModel;
using System.Linq;
using System.Threading.Tasks;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.Services;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Carrello : ContentPage
    {
        public ObservableCollection<Prodotto> cart;
        public DoOrdineServices shop;

        public Carrello()
        {
            BindingContext = this;
            shop = new DoOrdineServices();

            InitializeComponent();

            (App.Current as App).costoTotale = 0;
            cart = (App.Current as App).carrello;
            ShowCart.ItemsSource = cart;
            modificheFatte();

            ShowCart.ItemTapped += async (o, e) =>
            {
                var myList = (ListView)o;
                myList.SelectedItem = null;
            };

            aggiornaPeriodico();
        }

        private async void aggiornaPeriodico()
        {
            while (true)
            {
                await Task.Delay(1000);
                aggiornaCosto();
            }
        }

        public void remove(object sender, EventArgs e)
        {
            var button = ((Button)sender);
            Prodotto prodottoRimosso = (from itm in cart
                                        where itm.nome == button.CommandParameter.ToString()
                                        select itm)
                   .FirstOrDefault<Prodotto>();
            (App.Current as App).carrello.Remove(prodottoRimosso);
            modificheFatte();
        }

        private void modificheFatte()
        {
            cart = (App.Current as App).carrello;
            ShowCart.ItemsSource = cart;
            aggiornaCosto();
            Label_costoTotale.Text = (App.Current as App).costoTotale.ToString() + "$";
        }

        public void ProcedeOrder(object sender, EventArgs e)
        {
            int value;
            if ((App.Current as App).carrello.Count == 0)
            {
                DisplayAlert("Error", "Il carrello è vuoto", "OK");
            }
            else if (String.IsNullOrEmpty(Entry_citta.Text) || string.IsNullOrEmpty(Entry_via.Text) || string.IsNullOrEmpty(Entry_nr.Text))
            {
                DisplayAlert("Error", "Devi inserire tutte le informazioni relative all'indirizzo di spedizione!", "OK");
            }else if(!int.TryParse(Entry_nr.Text, out value)){
                DisplayAlert("Error", "Devi inserire un numero civico valido!", "OK");
            }
            else
            {
                if((App.Current as App).userLoggato.coin<(App.Current as App).costoTotale)
                {
                    DisplayAlert("Error", "Non hai abbastanza monete per completare l'ordine!", "OK");
                }
                else
                {
                    Ordine now = new Ordine(0, Entry_citta.Text, Entry_via.Text, int.Parse(Entry_nr.Text), 
                        (App.Current as App).carrello.ToArray(), (App.Current as App).userLoggato);
                    Ordine response = shop.doOrdine(now).Result;
                    if (response == null)
                    {
                        DisplayAlert("Error", "C'è stato un errore durante la transazione, prego riprovare!", "OK");
                    }
                    else
                    {
                        DisplayAlert("Done", "Ordine effettuato!", "OK");
                        (App.Current as App).userLoggato = response.utente;
                        (App.Current as App).carrello = new ObservableCollection<Prodotto>();
                        azzeraEntry();
                    }
                }
            }
            modificheFatte();
        }

        private void azzeraEntry()
        {
            Entry_citta.Text = "";
            Entry_via.Text = "";
            Entry_nr.Text = "";
        }

        public void aggiornaCosto()
        {
            double costoTotale = 0;
            foreach (Prodotto x in ((App.Current as App).carrello))
            {
                costoTotale += x.prezzo;
            }

            (App.Current as App).costoTotale = costoTotale;
            Label_costoTotale.Text = costoTotale.ToString() + "$";
        }
    }
}