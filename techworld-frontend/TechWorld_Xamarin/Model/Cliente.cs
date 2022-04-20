namespace TechWorld_Xamarin.Model
{
    public class Cliente
    {
        public string username { get; set; }
        public string password { get; set; }
        public string nome { get; set; }
        public string cognome { get; set; }
        public long cell { get; set; }
        public double coin { get; set; }
        public string email { get; set; }

        public Cliente(string user, string password, string nome, string cognome, long cell, double coin, string email)
        {
            this.username = user;
            this.password = password;
            this.nome = nome;
            this.cognome = cognome;
            this.cell = cell;
            this.coin = coin;
            this.email = email;
        }

        public Cliente() { }
    }
}
