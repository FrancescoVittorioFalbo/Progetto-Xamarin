using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace TechWorld_Xamarin.View
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Navigation : TabbedPage
    {

        public Navigation()
        {
            InitializeComponent();
            this.Children.Add(new ListaProdotti());
            this.Children.Add(new Carrello());
            this.Children.Add(new DettagliUtente());
        }
    }
}