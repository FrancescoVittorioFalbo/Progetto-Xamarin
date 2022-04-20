using System.Collections.ObjectModel;
using TechWorld_Xamarin.Model;
using TechWorld_Xamarin.View;
using Xamarin.Forms;

namespace TechWorld_Xamarin
{
    public partial class App : Application
    {
        public Page saved;
        public TechWorld_Xamarin.Model.Cliente userLoggato { get; set; }
        public ObservableCollection<Prodotto> carrello = new ObservableCollection<Prodotto>();
        public double costoTotale;

        public App()
        {
            InitializeComponent();

            MainPage = new NavigationPage(new LoginPage());
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
            saved = App.Current.MainPage;
        }

        protected override void OnResume()
        {
            App.Current.MainPage = saved;
        }
    }
}
