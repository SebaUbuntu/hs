namespace WindowsFormsAppProva
{
    partial class FormInizio
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
            this.buttonPopolaCombo = new System.Windows.Forms.Button();
            this.comboBoxStudenti = new System.Windows.Forms.ComboBox();
            this.textBoxStudente = new System.Windows.Forms.TextBox();
            this.textBoxStudenti = new System.Windows.Forms.TextBox();
            this.comboBoxClassi = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // buttonPopolaCombo
            // 
            this.buttonPopolaCombo.Location = new System.Drawing.Point(12, 20);
            this.buttonPopolaCombo.Name = "buttonPopolaCombo";
            this.buttonPopolaCombo.Size = new System.Drawing.Size(125, 40);
            this.buttonPopolaCombo.TabIndex = 0;
            this.buttonPopolaCombo.Text = "Popola combo";
            this.buttonPopolaCombo.UseVisualStyleBackColor = true;
            this.buttonPopolaCombo.Click += new System.EventHandler(this.buttonPopolaCombo_Click);
            // 
            // comboBoxStudenti
            // 
            this.comboBoxStudenti.FormattingEnabled = true;
            this.comboBoxStudenti.Location = new System.Drawing.Point(379, 30);
            this.comboBoxStudenti.Name = "comboBoxStudenti";
            this.comboBoxStudenti.Size = new System.Drawing.Size(202, 21);
            this.comboBoxStudenti.TabIndex = 1;
            // 
            // textBoxStudente
            // 
            this.textBoxStudente.Location = new System.Drawing.Point(160, 31);
            this.textBoxStudente.Name = "textBoxStudente";
            this.textBoxStudente.Size = new System.Drawing.Size(174, 20);
            this.textBoxStudente.TabIndex = 2;
            // 
            // textBoxStudenti
            // 
            this.textBoxStudenti.Location = new System.Drawing.Point(12, 88);
            this.textBoxStudenti.Multiline = true;
            this.textBoxStudenti.Name = "textBoxStudenti";
            this.textBoxStudenti.ReadOnly = true;
            this.textBoxStudenti.Size = new System.Drawing.Size(322, 174);
            this.textBoxStudenti.TabIndex = 3;
            // 
            // comboBoxClassi
            // 
            this.comboBoxClassi.FormattingEnabled = true;
            this.comboBoxClassi.Location = new System.Drawing.Point(475, 154);
            this.comboBoxClassi.Name = "comboBoxClassi";
            this.comboBoxClassi.Size = new System.Drawing.Size(121, 21);
            this.comboBoxClassi.TabIndex = 4;
            // 
            // FormInizio
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.comboBoxClassi);
            this.Controls.Add(this.textBoxStudenti);
            this.Controls.Add(this.textBoxStudente);
            this.Controls.Add(this.comboBoxStudenti);
            this.Controls.Add(this.buttonPopolaCombo);
            this.Name = "FormInizio";
            this.Text = "Tutorial C#";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button buttonPopolaCombo;
        private System.Windows.Forms.ComboBox comboBoxStudenti;
        private System.Windows.Forms.TextBox textBoxStudente;
        private System.Windows.Forms.TextBox textBoxStudenti;
        private System.Windows.Forms.ComboBox comboBoxClassi;
    }
}

