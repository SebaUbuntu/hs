namespace WindowsFormsDBArte
{
    partial class FormMain
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
            this.comboBoxArtisti = new System.Windows.Forms.ComboBox();
            this.comboBoxMusei = new System.Windows.Forms.ComboBox();
            this.buttonVediOpereMuseo = new System.Windows.Forms.Button();
            this.buttonVediOpereArtisti = new System.Windows.Forms.Button();
            this.labelMsg = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // comboBoxArtisti
            // 
            this.comboBoxArtisti.FormattingEnabled = true;
            this.comboBoxArtisti.Location = new System.Drawing.Point(36, 116);
            this.comboBoxArtisti.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.comboBoxArtisti.Name = "comboBoxArtisti";
            this.comboBoxArtisti.Size = new System.Drawing.Size(419, 28);
            this.comboBoxArtisti.TabIndex = 0;
            // 
            // comboBoxMusei
            // 
            this.comboBoxMusei.FormattingEnabled = true;
            this.comboBoxMusei.Location = new System.Drawing.Point(36, 202);
            this.comboBoxMusei.Name = "comboBoxMusei";
            this.comboBoxMusei.Size = new System.Drawing.Size(419, 28);
            this.comboBoxMusei.TabIndex = 1;
            // 
            // buttonVediOpereMuseo
            // 
            this.buttonVediOpereMuseo.Location = new System.Drawing.Point(462, 193);
            this.buttonVediOpereMuseo.Name = "buttonVediOpereMuseo";
            this.buttonVediOpereMuseo.Size = new System.Drawing.Size(217, 48);
            this.buttonVediOpereMuseo.TabIndex = 2;
            this.buttonVediOpereMuseo.Text = "Vedi opere Museo";
            this.buttonVediOpereMuseo.UseVisualStyleBackColor = true;
            this.buttonVediOpereMuseo.Click += new System.EventHandler(this.buttonVediOpereMuseo_Click);
            // 
            // buttonVediOpereArtisti
            // 
            this.buttonVediOpereArtisti.Location = new System.Drawing.Point(462, 109);
            this.buttonVediOpereArtisti.Name = "buttonVediOpereArtisti";
            this.buttonVediOpereArtisti.Size = new System.Drawing.Size(217, 45);
            this.buttonVediOpereArtisti.TabIndex = 3;
            this.buttonVediOpereArtisti.Text = "Vedi opere Artista";
            this.buttonVediOpereArtisti.UseVisualStyleBackColor = true;
            this.buttonVediOpereArtisti.Click += new System.EventHandler(this.buttonVediOpereArtisti_Click);
            // 
            // labelMsg
            // 
            this.labelMsg.AutoSize = true;
            this.labelMsg.ForeColor = System.Drawing.Color.DarkRed;
            this.labelMsg.Location = new System.Drawing.Point(1, 5);
            this.labelMsg.Name = "labelMsg";
            this.labelMsg.Size = new System.Drawing.Size(524, 20);
            this.labelMsg.TabIndex = 4;
            this.labelMsg.Text = "Questo programma necessita di una istanza avviata di MySql su localhost";
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(724, 286);
            this.Controls.Add(this.labelMsg);
            this.Controls.Add(this.buttonVediOpereArtisti);
            this.Controls.Add(this.buttonVediOpereMuseo);
            this.Controls.Add(this.comboBoxMusei);
            this.Controls.Add(this.comboBoxArtisti);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            this.Name = "FormMain";
            this.Text = "DB Arte";
            this.Load += new System.EventHandler(this.FormMain_Load);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ComboBox comboBoxArtisti;
        private System.Windows.Forms.ComboBox comboBoxMusei;
        private System.Windows.Forms.Button buttonVediOpereMuseo;
        private System.Windows.Forms.Button buttonVediOpereArtisti;
        private System.Windows.Forms.Label labelMsg;
    }
}

