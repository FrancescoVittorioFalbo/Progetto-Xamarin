namespace TechWorld_Xamarin.Model
{
    public class Prodotto
    {
        public int id { get; set; }
        public string nome { get; set; }
        public string marca { get; set; }
        public double prezzo { get; set; }
        public string descrizione { get; set; }
        public Categoria categoria { get; set; }
        public int qta { get; set; }
        public string link { get; set; }

        public Prodotto(int id, string nome, string marca, double prezzo, string descrizione, Categoria categoria, int qta, string link)
        {
            this.id = id;
            this.nome = nome;
            this.marca = marca;
            this.prezzo = prezzo;
            this.descrizione = descrizione;
            this.categoria = categoria;
            this.qta = qta;
            this.link = link;
        }

        public Prodotto() { }
    }
}
