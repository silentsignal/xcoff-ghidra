// This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild
package xcoff;

//This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild
//This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.util.Arrays;


/**
* @see <a href="https://www.ibm.com/docs/en/aix/7.2?topic=formats-xcoff-object-file-format">Source</a>
*/
public class Xcoff32 extends KaitaiStruct {
 public static Xcoff32 fromFile(String fileName) throws IOException {
     return new Xcoff32(new ByteBufferKaitaiStream(fileName));
 }

 public Xcoff32(KaitaiStream _io) {
     this(_io, null, null);
 }

 public Xcoff32(KaitaiStream _io, KaitaiStruct _parent) {
     this(_io, _parent, null);
 }

 public Xcoff32(KaitaiStream _io, KaitaiStruct _parent, Xcoff32 _root) {
     super(_io);
     this._parent = _parent;
     this._root = _root == null ? this : _root;
     _read();
 }
 private void _read() {
     this.header = new Header(this._io, this, _root);
     this.auxiliaryHeader = new AuxiliaryHeader(this._io, this, _root);
     this._raw_sectionHeaders = new ArrayList<byte[]>(((Number) (header().fNscns())).intValue());
     sectionHeaders = new ArrayList<SectionHeader>(((Number) (header().fNscns())).intValue());
     for (int i = 0; i < header().fNscns(); i++) {
         this._raw_sectionHeaders.add(this._io.readBytes(40));
         KaitaiStream _io__raw_sectionHeaders = new ByteBufferKaitaiStream(_raw_sectionHeaders.get(_raw_sectionHeaders.size() - 1));
         this.sectionHeaders.add(new SectionHeader(_io__raw_sectionHeaders, this, _root));
     }
 }
 public static class ImportEntry extends KaitaiStruct {
     public static ImportEntry fromFile(String fileName) throws IOException {
         return new ImportEntry(new ByteBufferKaitaiStream(fileName));
     }

     public ImportEntry(KaitaiStream _io) {
         this(_io, null, null);
     }

     public ImportEntry(KaitaiStream _io, Xcoff32.ImportTable _parent) {
         this(_io, _parent, null);
     }

     public ImportEntry(KaitaiStream _io, Xcoff32.ImportTable _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.lImpidpath = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
         this.lImpidbase = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
         this.lImpidmem = new String(this._io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
     }
     private String lImpidpath;
     private String lImpidbase;
     private String lImpidmem;
     private Xcoff32 _root;
     private Xcoff32.ImportTable _parent;
     public String lImpidpath() { return lImpidpath; }
     public String lImpidbase() { return lImpidbase; }
     public String lImpidmem() { return lImpidmem; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.ImportTable _parent() { return _parent; }
 }
 public static class ImportTable extends KaitaiStruct {
     public static ImportTable fromFile(String fileName) throws IOException {
         return new ImportTable(new ByteBufferKaitaiStream(fileName));
     }

     public ImportTable(KaitaiStream _io) {
         this(_io, null, null);
     }

     public ImportTable(KaitaiStream _io, Xcoff32.LoaderSection _parent) {
         this(_io, _parent, null);
     }

     public ImportTable(KaitaiStream _io, Xcoff32.LoaderSection _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.importEntries = new ArrayList<ImportEntry>();
         {
             int i = 0;
             while (!this._io.isEof()) {
                 this.importEntries.add(new ImportEntry(this._io, this, _root));
                 i++;
             }
         }
     }
     private ArrayList<ImportEntry> importEntries;
     private Xcoff32 _root;
     private Xcoff32.LoaderSection _parent;
     public ArrayList<ImportEntry> importEntries() { return importEntries; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.LoaderSection _parent() { return _parent; }
 }
 public static class LoaderSection extends KaitaiStruct {
     public static LoaderSection fromFile(String fileName) throws IOException {
         return new LoaderSection(new ByteBufferKaitaiStream(fileName));
     }

     public LoaderSection(KaitaiStream _io) {
         this(_io, null, null);
     }

     public LoaderSection(KaitaiStream _io, Xcoff32.SectionHeader _parent) {
         this(_io, _parent, null);
     }

     public LoaderSection(KaitaiStream _io, Xcoff32.SectionHeader _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.lVersion = this._io.readU4be();
         this.lNsyms = this._io.readU4be();
         this.lNreloc = this._io.readU4be();
         this.lIstlen = this._io.readU4be();
         this.lNimpid = this._io.readU4be();
         this.lImpoff = this._io.readU4be();
         this.lStlen = this._io.readU4be();
         this.lStoff = this._io.readU4be();
         this.symbolTable = new SymbolTable(this._io, this, _root);
         this.relocTable = new RelocationTable(this._io, this, _root);
         this._raw_importTable = this._io.readBytes(lIstlen());
         KaitaiStream _io__raw_importTable = new ByteBufferKaitaiStream(_raw_importTable);
         this.importTable = new ImportTable(_io__raw_importTable, this, _root);
         this._raw_stringTable = this._io.readBytes(lStlen());
         KaitaiStream _io__raw_stringTable = new ByteBufferKaitaiStream(_raw_stringTable);
         this.stringTable = new StringTable(_io__raw_stringTable, this, _root);
     }
     private long lVersion;
     private long lNsyms;
     private long lNreloc;
     private long lIstlen;
     private long lNimpid;
     private long lImpoff;
     private long lStlen;
     private long lStoff;
     private SymbolTable symbolTable;
     private RelocationTable relocTable;
     private ImportTable importTable;
     private StringTable stringTable;
     private Xcoff32 _root;
     private Xcoff32.SectionHeader _parent;
     private byte[] _raw_importTable;
     private byte[] _raw_stringTable;
     public long lVersion() { return lVersion; }
     public long lNsyms() { return lNsyms; }
     public long lNreloc() { return lNreloc; }
     public long lIstlen() { return lIstlen; }
     public long lNimpid() { return lNimpid; }
     public long lImpoff() { return lImpoff; }
     public long lStlen() { return lStlen; }
     public long lStoff() { return lStoff; }
     public SymbolTable symbolTable() { return symbolTable; }
     public RelocationTable relocTable() { return relocTable; }
     public ImportTable importTable() { return importTable; }
     public StringTable stringTable() { return stringTable; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.SectionHeader _parent() { return _parent; }
     public byte[] _raw_importTable() { return _raw_importTable; }
     public byte[] _raw_stringTable() { return _raw_stringTable; }
 }
 public static class CommonSection extends KaitaiStruct {
     public static CommonSection fromFile(String fileName) throws IOException {
         return new CommonSection(new ByteBufferKaitaiStream(fileName));
     }

     public CommonSection(KaitaiStream _io) {
         this(_io, null, null);
     }

     public CommonSection(KaitaiStream _io, Xcoff32.SectionHeader _parent) {
         this(_io, _parent, null);
     }

     public CommonSection(KaitaiStream _io, Xcoff32.SectionHeader _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.body = this._io.readBytes(_parent().sSize());
     }
     private byte[] body;
     private Xcoff32 _root;
     private Xcoff32.SectionHeader _parent;
     public byte[] body() { return body; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.SectionHeader _parent() { return _parent; }
 }
 public static class SymbolEntry extends KaitaiStruct {
     public static SymbolEntry fromFile(String fileName) throws IOException {
         return new SymbolEntry(new ByteBufferKaitaiStream(fileName));
     }

     public SymbolEntry(KaitaiStream _io) {
         this(_io, null, null);
     }

     public SymbolEntry(KaitaiStream _io, Xcoff32.SymbolTable _parent) {
         this(_io, _parent, null);
     }

     public SymbolEntry(KaitaiStream _io, Xcoff32.SymbolTable _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this._raw_nameStructure = this._io.readBytes(8);
         KaitaiStream _io__raw_nameStructure = new ByteBufferKaitaiStream(_raw_nameStructure);
         this.nameStructure = new SymbolName(_io__raw_nameStructure, this, _root);
         this.lValue = this._io.readU4be();
         this.lScnum = this._io.readU2be();
         this.lSmtype = this._io.readU1();
         this.lSmclas = this._io.readU1();
         this.lIfile = this._io.readU4be();
         this.lParam = this._io.readU4be();
     }
     private SymbolName nameStructure;
     private long lValue;
     private int lScnum;
     private int lSmtype;
     private int lSmclas;
     private long lIfile;
     private long lParam;
     private Xcoff32 _root;
     private Xcoff32.SymbolTable _parent;
     private byte[] _raw_nameStructure;
     public SymbolName nameStructure() { return nameStructure; }
     public long lValue() { return lValue; }
     public int lScnum() { return lScnum; }
     public int lSmtype() { return lSmtype; }
     public int lSmclas() { return lSmclas; }
     public long lIfile() { return lIfile; }
     public long lParam() { return lParam; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.SymbolTable _parent() { return _parent; }
     public byte[] _raw_nameStructure() { return _raw_nameStructure; }
 }
 public static class RelocationEntry extends KaitaiStruct {
     public static RelocationEntry fromFile(String fileName) throws IOException {
         return new RelocationEntry(new ByteBufferKaitaiStream(fileName));
     }

     public RelocationEntry(KaitaiStream _io) {
         this(_io, null, null);
     }

     public RelocationEntry(KaitaiStream _io, Xcoff32.RelocationTable _parent) {
         this(_io, _parent, null);
     }

     public RelocationEntry(KaitaiStream _io, Xcoff32.RelocationTable _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.lVaddr = this._io.readU4be();
         this.lSymndx = this._io.readU4be();
         this.lValue = this._io.readU2be();
         this.lRsecnm = this._io.readU2be();
     }
     private long lVaddr;
     private long lSymndx;
     private int lValue;
     private int lRsecnm;
     private Xcoff32 _root;
     private Xcoff32.RelocationTable _parent;
     public long lVaddr() { return lVaddr; }
     public long lSymndx() { return lSymndx; }
     public int lValue() { return lValue; }
     public int lRsecnm() { return lRsecnm; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.RelocationTable _parent() { return _parent; }
 }
 public static class SectionHeader extends KaitaiStruct {
     public static SectionHeader fromFile(String fileName) throws IOException {
         return new SectionHeader(new ByteBufferKaitaiStream(fileName));
     }

     public SectionHeader(KaitaiStream _io) {
         this(_io, null, null);
     }

     public SectionHeader(KaitaiStream _io, Xcoff32 _parent) {
         this(_io, _parent, null);
     }

     public SectionHeader(KaitaiStream _io, Xcoff32 _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.sName = new String(KaitaiStream.bytesTerminate(this._io.readBytes(8), (byte) 0, false), Charset.forName("ASCII"));
         this.sPaddr = this._io.readU4be();
         this.sVaddr = this._io.readU4be();
         this.sSize = this._io.readU4be();
         this.sScnptr = this._io.readU4be();
         this.sRelptr = this._io.readU4be();
         this.sLnnoptr = this._io.readU4be();
         this.sNreloc = this._io.readU2be();
         this.sNlnno = this._io.readU2be();
         this.sDummy = this._io.readU2be();
         this.sFlags = this._io.readU2be();
     }
     private KaitaiStruct subsection;
     public KaitaiStruct subsection() {
         if (this.subsection != null)
             return this.subsection;
         if (sScnptr() != 0) {
             KaitaiStream io = _root._io();
             long _pos = io.pos();
             io.seek(sScnptr());
             switch (sFlags()) {
             case 4096: {
                 this._raw_subsection = io.readBytes(sSize());
                 KaitaiStream _io__raw_subsection = new ByteBufferKaitaiStream(_raw_subsection);
                 this.subsection = new LoaderSection(_io__raw_subsection, this, _root);
                 break;
             }
             default: {
                 this._raw_subsection = io.readBytes(sSize());
                 KaitaiStream _io__raw_subsection = new ByteBufferKaitaiStream(_raw_subsection);
                 this.subsection = new CommonSection(_io__raw_subsection, this, _root);
                 break;
             }
             }
             io.seek(_pos);
         }
         return this.subsection;
     }
     private byte[] body;
     public byte[] body() {
         if (this.body != null)
             return this.body;
         if (sScnptr() != 0) {
             KaitaiStream io = _root._io();
             long _pos = io.pos();
             io.seek(sScnptr());
             this.body = io.readBytes(sSize());
             io.seek(_pos);
         }
         return this.body;
     }
     private String sName;
     private long sPaddr;
     private long sVaddr;
     private long sSize;
     private long sScnptr;
     private long sRelptr;
     private long sLnnoptr;
     private int sNreloc;
     private int sNlnno;
     private int sDummy;
     private int sFlags;
     private Xcoff32 _root;
     private Xcoff32 _parent;
     private byte[] _raw_subsection;
     public String sName() { return sName; }
     public long sPaddr() { return sPaddr; }
     public long sVaddr() { return sVaddr; }
     public long sSize() { return sSize; }
     public long sScnptr() { return sScnptr; }
     public long sRelptr() { return sRelptr; }
     public long sLnnoptr() { return sLnnoptr; }
     public int sNreloc() { return sNreloc; }
     public int sNlnno() { return sNlnno; }
     public int sDummy() { return sDummy; }
     public int sFlags() { return sFlags; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32 _parent() { return _parent; }
     public byte[] _raw_subsection() { return _raw_subsection; }
 }
 public static class RelocationTable extends KaitaiStruct {
     public static RelocationTable fromFile(String fileName) throws IOException {
         return new RelocationTable(new ByteBufferKaitaiStream(fileName));
     }

     public RelocationTable(KaitaiStream _io) {
         this(_io, null, null);
     }

     public RelocationTable(KaitaiStream _io, Xcoff32.LoaderSection _parent) {
         this(_io, _parent, null);
     }

     public RelocationTable(KaitaiStream _io, Xcoff32.LoaderSection _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         relocationEntries = new ArrayList<RelocationEntry>(((Number) (_parent().lNreloc())).intValue());
         for (int i = 0; i < _parent().lNreloc(); i++) {
             this.relocationEntries.add(new RelocationEntry(this._io, this, _root));
         }
     }
     private ArrayList<RelocationEntry> relocationEntries;
     private Xcoff32 _root;
     private Xcoff32.LoaderSection _parent;
     public ArrayList<RelocationEntry> relocationEntries() { return relocationEntries; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.LoaderSection _parent() { return _parent; }
 }
 public static class AuxiliaryHeader extends KaitaiStruct {
     public static AuxiliaryHeader fromFile(String fileName) throws IOException {
         return new AuxiliaryHeader(new ByteBufferKaitaiStream(fileName));
     }

     public AuxiliaryHeader(KaitaiStream _io) {
         this(_io, null, null);
     }

     public AuxiliaryHeader(KaitaiStream _io, Xcoff32 _parent) {
         this(_io, _parent, null);
     }

     public AuxiliaryHeader(KaitaiStream _io, Xcoff32 _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.oMflag = this._io.readU2be();
         this.oVstamp = this._io.readU2be();
         this.oTsize = this._io.readU4be();
         this.oDsize = this._io.readU4be();
         this.oBsize = this._io.readU4be();
         this.oEntry = this._io.readU4be();
         this.oTextStart = this._io.readU4be();
         this.oDataStart = this._io.readU4be();
         this.oToc = this._io.readU4be();
         this.oSnentry = this._io.readU2be();
         this.oSntext = this._io.readU2be();
         this.oSndata = this._io.readU2be();
         this.oSntoc = this._io.readU2be();
         this.oSnloader = this._io.readU2be();
         this.oSnbss = this._io.readU2be();
         this.oAlgntext = this._io.readU2be();
         this.oAlgndata = this._io.readU2be();
         this.oModtype = this._io.readU2be();
         this.oCpuflag = this._io.readU1();
         this.oCputype = this._io.readU1();
         this.oMaxstack = this._io.readU4be();
         this.oMaxdata = this._io.readU4be();
         this.oDebugger = this._io.readU4be();
         this.oTextpsize = this._io.readU1();
         this.oDatapsize = this._io.readU1();
         this.oStackpsize = this._io.readU1();
         this.oFlags = this._io.readU1();
         this.oSntdata = this._io.readU2be();
         this.oSntbss = this._io.readU2be();
     }
     private int oMflag;
     private int oVstamp;
     private long oTsize;
     private long oDsize;
     private long oBsize;
     private long oEntry;
     private long oTextStart;
     private long oDataStart;
     private long oToc;
     private int oSnentry;
     private int oSntext;
     private int oSndata;
     private int oSntoc;
     private int oSnloader;
     private int oSnbss;
     private int oAlgntext;
     private int oAlgndata;
     private int oModtype;
     private int oCpuflag;
     private int oCputype;
     private long oMaxstack;
     private long oMaxdata;
     private long oDebugger;
     private int oTextpsize;
     private int oDatapsize;
     private int oStackpsize;
     private int oFlags;
     private int oSntdata;
     private int oSntbss;
     private Xcoff32 _root;
     private Xcoff32 _parent;
     public int oMflag() { return oMflag; }
     public int oVstamp() { return oVstamp; }
     public long oTsize() { return oTsize; }
     public long oDsize() { return oDsize; }
     public long oBsize() { return oBsize; }
     public long oEntry() { return oEntry; }
     public long oTextStart() { return oTextStart; }
     public long oDataStart() { return oDataStart; }
     public long oToc() { return oToc; }
     public int oSnentry() { return oSnentry; }
     public int oSntext() { return oSntext; }
     public int oSndata() { return oSndata; }
     public int oSntoc() { return oSntoc; }
     public int oSnloader() { return oSnloader; }
     public int oSnbss() { return oSnbss; }
     public int oAlgntext() { return oAlgntext; }
     public int oAlgndata() { return oAlgndata; }
     public int oModtype() { return oModtype; }
     public int oCpuflag() { return oCpuflag; }
     public int oCputype() { return oCputype; }
     public long oMaxstack() { return oMaxstack; }
     public long oMaxdata() { return oMaxdata; }
     public long oDebugger() { return oDebugger; }
     public int oTextpsize() { return oTextpsize; }
     public int oDatapsize() { return oDatapsize; }
     public int oStackpsize() { return oStackpsize; }
     public int oFlags() { return oFlags; }
     public int oSntdata() { return oSntdata; }
     public int oSntbss() { return oSntbss; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32 _parent() { return _parent; }
 }
 public static class StringEntry extends KaitaiStruct {
     public static StringEntry fromFile(String fileName) throws IOException {
         return new StringEntry(new ByteBufferKaitaiStream(fileName));
     }

     public StringEntry(KaitaiStream _io) {
         this(_io, null, null);
     }

     public StringEntry(KaitaiStream _io, Xcoff32.StringTable _parent) {
         this(_io, _parent, null);
     }

     public StringEntry(KaitaiStream _io, Xcoff32.StringTable _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.strlen = this._io.readU2be();
         this.str = new String(KaitaiStream.bytesTerminate(this._io.readBytes(strlen()), (byte) 0, false), Charset.forName("ASCII"));
     }
     private int strlen;
     private String str;
     private Xcoff32 _root;
     private Xcoff32.StringTable _parent;
     public int strlen() { return strlen; }
     public String str() { return str; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.StringTable _parent() { return _parent; }
 }
 public static class Header extends KaitaiStruct {
     public static Header fromFile(String fileName) throws IOException {
         return new Header(new ByteBufferKaitaiStream(fileName));
     }

     public Header(KaitaiStream _io) {
         this(_io, null, null);
     }

     public Header(KaitaiStream _io, Xcoff32 _parent) {
         this(_io, _parent, null);
     }

     public Header(KaitaiStream _io, Xcoff32 _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.fMagic = this._io.readBytes(2);
         if (!(Arrays.equals(fMagic(), new byte[] { 1, -33 }))) {
             throw new KaitaiStream.ValidationNotEqualError(new byte[] { 1, -33 }, fMagic(), _io(), "/types/header/seq/0");
         }
         this.fNscns = this._io.readU2be();
         this.fTimdat = this._io.readU4be();
         this.fSymptr = this._io.readU4be();
         this.fNsyms = this._io.readU4be();
         this.fOpthdr = this._io.readU2be();
         this.fFlags = this._io.readU2be();
     }
     private byte[] fMagic;
     private int fNscns;
     private long fTimdat;
     private long fSymptr;
     private long fNsyms;
     private int fOpthdr;
     private int fFlags;
     private Xcoff32 _root;
     private Xcoff32 _parent;
     public byte[] fMagic() { return fMagic; }
     public int fNscns() { return fNscns; }
     public long fTimdat() { return fTimdat; }
     public long fSymptr() { return fSymptr; }
     public long fNsyms() { return fNsyms; }
     public int fOpthdr() { return fOpthdr; }
     public int fFlags() { return fFlags; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32 _parent() { return _parent; }
 }
 public static class StringTable extends KaitaiStruct {
     public static StringTable fromFile(String fileName) throws IOException {
         return new StringTable(new ByteBufferKaitaiStream(fileName));
     }

     public StringTable(KaitaiStream _io) {
         this(_io, null, null);
     }

     public StringTable(KaitaiStream _io, Xcoff32.LoaderSection _parent) {
         this(_io, _parent, null);
     }

     public StringTable(KaitaiStream _io, Xcoff32.LoaderSection _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.stringEntries = new ArrayList<StringEntry>();
         {
             int i = 0;
             while (!this._io.isEof()) {
                 this.stringEntries.add(new StringEntry(this._io, this, _root));
                 i++;
             }
         }
     }
     private ArrayList<StringEntry> stringEntries;
     private Xcoff32 _root;
     private Xcoff32.LoaderSection _parent;
     public ArrayList<StringEntry> stringEntries() { return stringEntries; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.LoaderSection _parent() { return _parent; }
 }
 public static class SymbolName extends KaitaiStruct {
     public static SymbolName fromFile(String fileName) throws IOException {
         return new SymbolName(new ByteBufferKaitaiStream(fileName));
     }

     public SymbolName(KaitaiStream _io) {
         this(_io, null, null);
     }

     public SymbolName(KaitaiStream _io, Xcoff32.SymbolEntry _parent) {
         this(_io, _parent, null);
     }

     public SymbolName(KaitaiStream _io, Xcoff32.SymbolEntry _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.lZeroes = this._io.readU4be();
         this.lOffset = this._io.readU4be();
     }
     private String lName;
     public String lName() {
         if (this.lName != null)
             return this.lName;
         long _pos = this._io.pos();
         this._io.seek(0);
         this.lName = new String(KaitaiStream.bytesTerminate(this._io.readBytes(8), (byte) 0, false), Charset.forName("ASCII"));
         this._io.seek(_pos);
         return this.lName;
     }
     private String lStrname;
     public String lStrname() {
         if (this.lStrname != null)
             return this.lStrname;
         if (lZeroes() == 0) {
             KaitaiStream io = _parent()._parent()._parent().stringTable()._io();
             long _pos = io.pos();
             io.seek(lOffset());
             this.lStrname = new String(io.readBytesTerm(0, false, true, true), Charset.forName("ASCII"));
             io.seek(_pos);
         }
         return this.lStrname;
     }
     private long lZeroes;
     private long lOffset;
     private Xcoff32 _root;
     private Xcoff32.SymbolEntry _parent;
     public long lZeroes() { return lZeroes; }
     public long lOffset() { return lOffset; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.SymbolEntry _parent() { return _parent; }
 }
 public static class SymbolTable extends KaitaiStruct {
     public static SymbolTable fromFile(String fileName) throws IOException {
         return new SymbolTable(new ByteBufferKaitaiStream(fileName));
     }

     public SymbolTable(KaitaiStream _io) {
         this(_io, null, null);
     }

     public SymbolTable(KaitaiStream _io, Xcoff32.LoaderSection _parent) {
         this(_io, _parent, null);
     }

     public SymbolTable(KaitaiStream _io, Xcoff32.LoaderSection _parent, Xcoff32 _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this._raw_symbolEntries = new ArrayList<byte[]>(((Number) (_parent().lNsyms())).intValue());
         symbolEntries = new ArrayList<SymbolEntry>(((Number) (_parent().lNsyms())).intValue());
         for (int i = 0; i < _parent().lNsyms(); i++) {
             this._raw_symbolEntries.add(this._io.readBytes(24));
             KaitaiStream _io__raw_symbolEntries = new ByteBufferKaitaiStream(_raw_symbolEntries.get(_raw_symbolEntries.size() - 1));
             this.symbolEntries.add(new SymbolEntry(_io__raw_symbolEntries, this, _root));
         }
     }
     private ArrayList<SymbolEntry> symbolEntries;
     private Xcoff32 _root;
     private Xcoff32.LoaderSection _parent;
     private ArrayList<byte[]> _raw_symbolEntries;
     public ArrayList<SymbolEntry> symbolEntries() { return symbolEntries; }
     public Xcoff32 _root() { return _root; }
     public Xcoff32.LoaderSection _parent() { return _parent; }
     public ArrayList<byte[]> _raw_symbolEntries() { return _raw_symbolEntries; }
 }
 private Header header;
 private AuxiliaryHeader auxiliaryHeader;
 private ArrayList<SectionHeader> sectionHeaders;
 private Xcoff32 _root;
 private KaitaiStruct _parent;
 private ArrayList<byte[]> _raw_sectionHeaders;
 public Header header() { return header; }
 public AuxiliaryHeader auxiliaryHeader() { return auxiliaryHeader; }
 public ArrayList<SectionHeader> sectionHeaders() { return sectionHeaders; }
 public Xcoff32 _root() { return _root; }
 public KaitaiStruct _parent() { return _parent; }
 public ArrayList<byte[]> _raw_sectionHeaders() { return _raw_sectionHeaders; }
}
