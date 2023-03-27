
namespace WindowsFormsAppFilm
{
    partial class WindowsFormCategorie
    {
        /// <summary>
        /// Variabile di progettazione necessaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Pulire le risorse in uso.
        /// </summary>
        /// <param name="disposing">ha valore true se le risorse gestite devono essere eliminate, false in caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Codice generato da Progettazione Windows Form

        /// <summary>
        /// Metodo necessario per il supporto della finestra di progettazione. Non modificare
        /// il contenuto del metodo con l'editor di codice.
        /// </summary>
        private void InitializeComponent()
        {
            this.comboBoxCategorie = new System.Windows.Forms.ComboBox();
            this.buttonOpenCategoria = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // comboBoxCategorie
            // 
            this.comboBoxCategorie.FormattingEnabled = true;
            this.comboBoxCategorie.Location = new System.Drawing.Point(32, 36);
            this.comboBoxCategorie.Name = "comboBoxCategorie";
            this.comboBoxCategorie.Size = new System.Drawing.Size(121, 21);
            this.comboBoxCategorie.TabIndex = 0;
            // 
            // buttonOpenCategoria
            // 
            this.buttonOpenCategoria.Location = new System.Drawing.Point(159, 36);
            this.buttonOpenCategoria.Name = "buttonOpenCategoria";
            this.buttonOpenCategoria.Size = new System.Drawing.Size(75, 23);
            this.buttonOpenCategoria.TabIndex = 1;
            this.buttonOpenCategoria.Text = "Visualizza categoria";
            this.buttonOpenCategoria.UseVisualStyleBackColor = true;
            this.buttonOpenCategoria.Click += new System.EventHandler(this.buttonOpenCategoria_Click);
            // 
            // WindowsFormCategorie
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.buttonOpenCategoria);
            this.Controls.Add(this.comboBoxCategorie);
            this.Name = "WindowsFormCategorie";
            this.Text = "Categorie";
            this.Load += new System.EventHandler(this.WindowsFormCategorie_Load);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.ComboBox comboBoxCategorie;
        private System.Windows.Forms.Button buttonOpenCategoria;
    }
}

