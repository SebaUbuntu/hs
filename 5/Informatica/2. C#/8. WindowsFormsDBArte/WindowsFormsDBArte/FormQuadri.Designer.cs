namespace WindowsFormsDBArte
{
    partial class FormQuadri
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridViewQuadri = new System.Windows.Forms.DataGridView();
            this.buttonUpdateQuadri = new System.Windows.Forms.Button();
            this.labelMsg = new System.Windows.Forms.Label();
            this.labelTitolo = new System.Windows.Forms.Label();
            this.comboBoxCodice = new System.Windows.Forms.ComboBox();
            this.groupBoxNuovoQuadro = new System.Windows.Forms.GroupBox();
            this.label5 = new System.Windows.Forms.Label();
            this.textBoxNote = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.textBoxLarghezza = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.textBoxAltezza = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.textBoxTecnica = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.textBoxAnno = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.textBoxTitolo = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewQuadri)).BeginInit();
            this.groupBoxNuovoQuadro.SuspendLayout();
            this.SuspendLayout();
            // 
            // dataGridViewQuadri
            // 
            this.dataGridViewQuadri.AllowUserToAddRows = false;
            this.dataGridViewQuadri.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.AllCells;
            this.dataGridViewQuadri.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewQuadri.Location = new System.Drawing.Point(12, 103);
            this.dataGridViewQuadri.Name = "dataGridViewQuadri";
            this.dataGridViewQuadri.RowHeadersWidthSizeMode = System.Windows.Forms.DataGridViewRowHeadersWidthSizeMode.AutoSizeToAllHeaders;
            this.dataGridViewQuadri.RowTemplate.Height = 24;
            this.dataGridViewQuadri.Size = new System.Drawing.Size(1160, 400);
            this.dataGridViewQuadri.TabIndex = 0;
            // 
            // buttonUpdateQuadri
            // 
            this.buttonUpdateQuadri.Font = new System.Drawing.Font("Microsoft Sans Serif", 11F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.buttonUpdateQuadri.ForeColor = System.Drawing.Color.DarkRed;
            this.buttonUpdateQuadri.Location = new System.Drawing.Point(972, 534);
            this.buttonUpdateQuadri.Margin = new System.Windows.Forms.Padding(4);
            this.buttonUpdateQuadri.Name = "buttonUpdateQuadri";
            this.buttonUpdateQuadri.Size = new System.Drawing.Size(200, 38);
            this.buttonUpdateQuadri.TabIndex = 1;
            this.buttonUpdateQuadri.Text = "Aggiorna tabella quadri";
            this.buttonUpdateQuadri.UseVisualStyleBackColor = true;
            this.buttonUpdateQuadri.Click += new System.EventHandler(this.buttonUpdateQuadri_Click);
            // 
            // labelMsg
            // 
            this.labelMsg.AutoSize = true;
            this.labelMsg.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelMsg.ForeColor = System.Drawing.Color.DarkRed;
            this.labelMsg.Location = new System.Drawing.Point(13, 52);
            this.labelMsg.Name = "labelMsg";
            this.labelMsg.Size = new System.Drawing.Size(1111, 20);
            this.labelMsg.TabIndex = 2;
            this.labelMsg.Text = "Aggiorna tabella QUADRI: modifica i campi o cancella direttamente sulla griglia. " +
    "Inserisci righe nel riquadro sotto. Premi il bottone Aggiorna per salvare sul DB" +
    "";
            // 
            // labelTitolo
            // 
            this.labelTitolo.AutoSize = true;
            this.labelTitolo.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTitolo.ForeColor = System.Drawing.Color.DarkRed;
            this.labelTitolo.Location = new System.Drawing.Point(17, 13);
            this.labelTitolo.Name = "labelTitolo";
            this.labelTitolo.Size = new System.Drawing.Size(93, 24);
            this.labelTitolo.TabIndex = 4;
            this.labelTitolo.Text = "label titolo";
            // 
            // comboBoxCodice
            // 
            this.comboBoxCodice.FormattingEnabled = true;
            this.comboBoxCodice.Location = new System.Drawing.Point(123, 63);
            this.comboBoxCodice.Name = "comboBoxCodice";
            this.comboBoxCodice.Size = new System.Drawing.Size(316, 26);
            this.comboBoxCodice.TabIndex = 5;
            // 
            // groupBoxNuovoQuadro
            // 
            this.groupBoxNuovoQuadro.BackColor = System.Drawing.Color.PowderBlue;
            this.groupBoxNuovoQuadro.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            this.groupBoxNuovoQuadro.Controls.Add(this.label5);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxNote);
            this.groupBoxNuovoQuadro.Controls.Add(this.label6);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxLarghezza);
            this.groupBoxNuovoQuadro.Controls.Add(this.label7);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxAltezza);
            this.groupBoxNuovoQuadro.Controls.Add(this.label4);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxTecnica);
            this.groupBoxNuovoQuadro.Controls.Add(this.label3);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxAnno);
            this.groupBoxNuovoQuadro.Controls.Add(this.label2);
            this.groupBoxNuovoQuadro.Controls.Add(this.textBoxTitolo);
            this.groupBoxNuovoQuadro.Controls.Add(this.label1);
            this.groupBoxNuovoQuadro.Controls.Add(this.comboBoxCodice);
            this.groupBoxNuovoQuadro.ForeColor = System.Drawing.Color.DarkBlue;
            this.groupBoxNuovoQuadro.Location = new System.Drawing.Point(8, 523);
            this.groupBoxNuovoQuadro.Name = "groupBoxNuovoQuadro";
            this.groupBoxNuovoQuadro.Size = new System.Drawing.Size(950, 180);
            this.groupBoxNuovoQuadro.TabIndex = 6;
            this.groupBoxNuovoQuadro.TabStop = false;
            this.groupBoxNuovoQuadro.Text = "Nuovo Quadro";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(473, 137);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(40, 18);
            this.label5.TabIndex = 18;
            this.label5.Text = "Note";
            // 
            // textBoxNote
            // 
            this.textBoxNote.Location = new System.Drawing.Point(583, 135);
            this.textBoxNote.Name = "textBoxNote";
            this.textBoxNote.Size = new System.Drawing.Size(314, 24);
            this.textBoxNote.TabIndex = 17;
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(473, 102);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(77, 18);
            this.label6.TabIndex = 16;
            this.label6.Text = "Larghezza";
            // 
            // textBoxLarghezza
            // 
            this.textBoxLarghezza.Location = new System.Drawing.Point(583, 100);
            this.textBoxLarghezza.Name = "textBoxLarghezza";
            this.textBoxLarghezza.Size = new System.Drawing.Size(123, 24);
            this.textBoxLarghezza.TabIndex = 15;
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(473, 67);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(56, 18);
            this.label7.TabIndex = 14;
            this.label7.Text = "Altezza";
            // 
            // textBoxAltezza
            // 
            this.textBoxAltezza.Location = new System.Drawing.Point(583, 63);
            this.textBoxAltezza.Name = "textBoxAltezza";
            this.textBoxAltezza.Size = new System.Drawing.Size(123, 24);
            this.textBoxAltezza.TabIndex = 13;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(10, 138);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(60, 18);
            this.label4.TabIndex = 12;
            this.label4.Text = "Tecnica";
            // 
            // textBoxTecnica
            // 
            this.textBoxTecnica.Location = new System.Drawing.Point(122, 134);
            this.textBoxTecnica.Name = "textBoxTecnica";
            this.textBoxTecnica.Size = new System.Drawing.Size(314, 24);
            this.textBoxTecnica.TabIndex = 11;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(14, 100);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(42, 18);
            this.label3.TabIndex = 10;
            this.label3.Text = "Anno";
            // 
            // textBoxAnno
            // 
            this.textBoxAnno.Location = new System.Drawing.Point(123, 100);
            this.textBoxAnno.Name = "textBoxAnno";
            this.textBoxAnno.Size = new System.Drawing.Size(123, 24);
            this.textBoxAnno.TabIndex = 9;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(14, 27);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(95, 18);
            this.label2.TabIndex = 8;
            this.label2.Text = "Titolo quadro";
            // 
            // textBoxTitolo
            // 
            this.textBoxTitolo.Location = new System.Drawing.Point(125, 26);
            this.textBoxTitolo.Name = "textBoxTitolo";
            this.textBoxTitolo.Size = new System.Drawing.Size(773, 24);
            this.textBoxTitolo.TabIndex = 7;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(14, 66);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(101, 18);
            this.label1.TabIndex = 6;
            this.label1.Text = "Autore/Museo";
            // 
            // FormQuadri
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1184, 718);
            this.Controls.Add(this.groupBoxNuovoQuadro);
            this.Controls.Add(this.labelTitolo);
            this.Controls.Add(this.labelMsg);
            this.Controls.Add(this.buttonUpdateQuadri);
            this.Controls.Add(this.dataGridViewQuadri);
            this.Font = new System.Drawing.Font("Microsoft Sans Serif", 11F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Name = "FormQuadri";
            this.Text = "Gestione Quadri";
            this.Load += new System.EventHandler(this.FormQuadri_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewQuadri)).EndInit();
            this.groupBoxNuovoQuadro.ResumeLayout(false);
            this.groupBoxNuovoQuadro.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridViewQuadri;
        private System.Windows.Forms.Button buttonUpdateQuadri;
        private System.Windows.Forms.Label labelMsg;
        private System.Windows.Forms.Label labelTitolo;
        private System.Windows.Forms.ComboBox comboBoxCodice;
        private System.Windows.Forms.GroupBox groupBoxNuovoQuadro;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textBoxNote;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textBoxLarghezza;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textBoxAltezza;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textBoxTecnica;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox textBoxAnno;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textBoxTitolo;
        private System.Windows.Forms.Label label1;
    }
}