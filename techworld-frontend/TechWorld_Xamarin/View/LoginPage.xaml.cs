using System;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.Services;
using Xamarin.Forms;

namespace TechWorld_Xamarin.View
{
    public partial class LoginPage : ContentPage
    {
        private LoginService login;

        public LoginPage()
        {
            InitializeComponent();
            login = new LoginService();
        }

        async void SignInProcedure(object sender, EventArgs e)
        {
            if (String.IsNullOrWhiteSpace(Entry_Username.Text) && String.IsNullOrWhiteSpace(Entry_Password.Text))
            {
                await DisplayAlert("Attenzione", "Username o Password non forniti!", "Ok");
            }
            else if (String.IsNullOrWhiteSpace(Entry_Password.Text))
            {
                await DisplayAlert("Attenzione", "La password non è stata fornita!", "Ok");
            }
            else if (String.IsNullOrWhiteSpace(Entry_Username.Text))
            {
                await DisplayAlert("Attenzione", "L'username non è stato fornito!", "Ok");
            }
            else
            {
                var log = login.Login(Entry_Username.Text, Entry_Password.Text);
                Cliente loggato = log.Result;
                if (loggato != null)
                {
                    AccessoEffettuato(loggato);
                }
                else
                {
                    await DisplayAlert("Errore", "Credenziali errate, riprova!", "Ok");
                }
            }
        }

        async private void AccessoEffettuato(Cliente loggato)
        {
            (App.Current as App).userLoggato = loggato;
            App.Current.MainPage = new Navigation();
        }

        private async void TapGestureRecognizer_Tapped(object sender, EventArgs e)
        {
            await Navigation.PushAsync(new RegisterPage());
        }
    }
}