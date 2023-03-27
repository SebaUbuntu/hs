namespace WindowsFormsAppVerifica2
{
    partial class FormNegozioMultimediale
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
            this.dataGridViewNegozio = new System.Windows.Forms.DataGridView();
            this.buttonOrdinaPerCosto = new System.Windows.Forms.Button();
            this.buttonOrdinaPerTitolo = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewNegozio)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridViewNegozio
            // 
            this.dataGridViewNegozio.AllowUserToAddRows = false;
            this.dataGridViewNegozio.AllowUserToDeleteRows = false;
            this.dataGridViewNegozio.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewNegozio.Location = new System.Drawing.Point(12, 12);
            this.dataGridViewNegozio.Name = "dataGridViewNegozio";
            this.dataGridViewNegozio.ReadOnly = true;
            this.dataGridViewNegozio.Size = new System.Drawing.Size(663, 426);
            this.dataGridViewNegozio.TabIndex = 0;
            // 
            // buttonOrdinaPerCosto
            // 
            this.buttonOrdinaPerCosto.Location = new System.Drawing.Point(682, 13);
            this.buttonOrdinaPerCosto.Name = "buttonOrdinaPerCosto";
            this.buttonOrdinaPerCosto.Size = new System.Drawing.Size(106, 23);
            this.buttonOrdinaPerCosto.TabIndex = 1;
            this.buttonOrdinaPerCosto.Text = "Ordina per costo";
            this.buttonOrdinaPerCosto.UseVisualStyleBackColor = true;
            this.buttonOrdinaPerCosto.Click += new System.EventHandler(this.buttonOrdinaPerCosto_Click);
            // 
            // buttonOrdinaPerTitolo
            // 
            this.buttonOrdinaPerTitolo.Location = new System.Drawing.Point(682, 43);
            this.buttonOrdinaPerTitolo.Name = "buttonOrdinaPerTitolo";
            this.buttonOrdinaPerTitolo.Size = new System.Drawing.Size(106, 23);
            this.buttonOrdinaPerTitolo.TabIndex = 2;
            this.buttonOrdinaPerTitolo.Text = "Ordina per titolo";
            this.buttonOrdinaPerTitolo.UseVisualStyleBackColor = true;
            this.buttonOrdinaPerTitolo.Click += new System.EventHandler(this.buttonOrdinaPerTitolo_Click);
            // 
            // FormNegozioMultimediale
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.buttonOrdinaPerTitolo);
            this.Controls.Add(this.buttonOrdinaPerCosto);
            this.Controls.Add(this.dataGridViewNegozio);
            this.Name = "FormNegozioMultimediale";
            this.Text = "FormNegozioMultimediale";
            this.Load += new System.EventHandler(this.FormNegozioMultimediale_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewNegozio)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewNegozio;
        private System.Windows.Forms.Button buttonOrdinaPerCosto;
        private System.Windows.Forms.Button buttonOrdinaPerTitolo;
    }
}

