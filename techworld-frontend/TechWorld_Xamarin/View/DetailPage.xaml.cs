using System;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class DetailPage : ContentPage
    {
        public TechWorld_Xamarin.Model.Prodotto now;

        public DetailPage(Model.Prodotto myProduct)
        {
            now = myProduct;
            InitializeComponent();

            show();
        }

        private void show()
        {
            Label_title.Text = now.categoria.nome + " - " + now.nome;
            Label_marca.Text = now.marca;
            Label_prezzo.Text = now.prezzo.ToString()+"$";
            Label_descrizione.Text = now.descrizione;
            Label_qta.Text = now.qta.ToString();

            Image_link.Source = now.link;
        }

        private void AddCart_Clicked(object sender, EventArgs e)
        {
            Boolean found = false;
            foreach (Model.Prodotto p in (App.Current as App).carrello)
            {
                if (p.id.Equals(now.id)) found = true;
            }

            if (found)
            {
                DisplayAlert("Error", "Il prodotto selezionato si trova già nel carrello!", "Ok");
            }
            else
            {
                if (now.qta == 0)
                {
                    DisplayAlert("Error", "Quantità non disponibile!", "Ok");
                }
                else
                {
                    (App.Current as App).carrello.Add(now);
                    (App.Current as App).costoTotale += now.prezzo;
                    DisplayAlert("Aggiunto", now.nome.ToUpper() + " è stato aggiunto al carrello", "Ok");
                }
            }
        }
    }
}