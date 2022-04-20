using System;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.Services;
using Xamarin.Forms;

namespace TechWorld_Xamarin.View
{
    public partial class RegisterPage : ContentPage
    {
        private LoginService register;

        public RegisterPage()
        {
            InitializeComponent();
            register = new LoginService();
        }

        async void RegisterProcedure(object sender, EventArgs e)
        {
            long value;
            if (String.IsNullOrWhiteSpace(Entry_Username.Text) || String.IsNullOrWhiteSpace(Entry_Password.Text) ||
                String.IsNullOrWhiteSpace(Entry_Nome.Text) || String.IsNullOrWhiteSpace(Entry_Cognome.Text) ||
                String.IsNullOrWhiteSpace(Entry_Email.Text) || String.IsNullOrWhiteSpace(Entry_PhoneNumber.Text))
            {
                await DisplayAlert("Attenzione", "Mancano dei dati!", "Ok");
            }
            else if (!long.TryParse(Entry_PhoneNumber.Text, out value))
            {
                await DisplayAlert("Attenzione", "Devi inserire un numero di telefono valido!", "Ok");
            }
            else
            {
                Cliente registrato = new Cliente(Entry_Username.Text, Entry_Password.Text, Entry_Nome.Text, Entry_Cognome.Text, Convert.ToInt64(Entry_PhoneNumber.Text), 0, Entry_Email.Text);

                var log = register.Register(registrato);

                Cliente loggato = log.Result;
                if (loggato != null)
                {
                    AccessoEffettuato(loggato);
                }
                else
                {
                    await DisplayAlert("Errore", "Esiste già un cliente con lo stesso username o numero di telefono o email!", "Ok");
                }

            }
        }

        private void AccessoEffettuato(Cliente loggato)
        {
            (App.Current as App).userLoggato = loggato;
            App.Current.MainPage = new Navigation();
        }
    }
}